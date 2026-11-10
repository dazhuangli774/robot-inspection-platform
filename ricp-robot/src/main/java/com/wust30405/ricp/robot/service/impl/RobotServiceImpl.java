package com.wust30405.ricp.robot.service.impl;

import ClientDemo.ClientDemo;
import ClientDemo.HCNetSDK;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wust30405.ricp.common.util.HttpUtils;
import com.wust30405.ricp.detect.factory.ADetector;
import com.wust30405.ricp.record.entity.Record;
import com.wust30405.ricp.robot.feign.*;
import com.wust30405.ricp.robot.service.IArmMoveService;
import com.wust30405.ricp.robot.service.ICameraService;
import com.wust30405.ricp.robot.service.IRobotService;
import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.entity.Task;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RobotServiceImpl implements IRobotService {

    private static String robot_url = "http://192.168.10.10:9001/api/";

    private static Logger _log = LoggerFactory.getLogger(RobotServiceImpl.class);

    private final IArmMoveService armMoveService;

    private final ICameraService cameraService;

    private final DetectFeign detectFeign;

    private final TaskFeign taskFeign;

    private final DeviceFeign deviceFeign;

    private final XarmFeign xarmFeign;

    private final RecordFeign recordFeign;

    public RobotServiceImpl(IArmMoveService armMoveService, ICameraService cameraService, DetectFeign detectFeign, TaskFeign taskFeign, DeviceFeign deviceFeign, XarmFeign xarmFeign, RecordFeign recordFeign) {
        this.armMoveService = armMoveService;
        this.cameraService = cameraService;
        this.detectFeign = detectFeign;
        this.taskFeign = taskFeign;
        this.deviceFeign = deviceFeign;
        this.xarmFeign = xarmFeign;
        this.recordFeign = recordFeign;
    }

    @Override
    public JsonObject robotControl(String order, String param) {
        String data = HttpUtils.sendGet(robot_url + order,param);
        JsonParser parser = new JsonParser();
        //json 转对象
        if(!"".equals(data)){
            JsonObject object=(JsonObject) parser.parse(data);

            return object;
        }
        return null;
    }



    @Override
    public String overallRobotStatus() {
        JsonObject object = robotControl("robot_status","null");
        JsonElement status = object.get("results");
        String str = status.toString();
        return str;
    }

    @Override
    public String robotStatus(String statusName) {
        try {
            String data = HttpUtils.sendGet("http://192.168.10.10:9001/api/robot_status",null);
            if(!"".equals(data)){
                JsonParser parser = new JsonParser();
                //json 转对象
                JsonObject object=(JsonObject) parser.parse(data);
                //System.out.println(object);
                JsonElement status = object.get("results");
                if (status != null){
                    String str = status.toString();
                    JsonObject object1 = (JsonObject)parser.parse(str);
                    String move_status = object1.get(statusName)!=null?object1.get(statusName).toString():"";
                    return move_status;
                }
            }
        } catch (Exception e) {
            System.out.println("获取状态失败："+e);
        }
        return "";
    }

    @Override
    public String robotStatusString() {
        String data = HttpUtils.sendGet("http://192.168.10.10:9001/api/robot_status",null);
        int i=0;
        while("".equals(data)){
            data = HttpUtils.sendGet("http://192.168.10.10:9001/api/robot_status",null);
            ++i;
            _log.info("机器人定点巡检判断是否可以开始,请求已重发："+(i));
        }

        JsonParser parser = new JsonParser();

        //json 转对象
        JsonObject object=(JsonObject) parser.parse(data);

        JsonElement status = object.get("results");

        if(status!=null){

            String str = status.toString();


            return str;
        }
        return null;
    }

    @Override
    public String setEstop(String flag) {
        JsonObject object = robotControl("estop","flag=" + flag);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String robotStatus = robotStatus("soft_estop_state");
        System.out.println(robotStatus);
        if ("true".equals(robotStatus)){
            return "机器人已急停";
        }
        return "急停已解除";
    }

    @Override
    public void performInspectionTasks(List<Integer> tasksList) {
        /*初始化相机*/
        ClientDemo demo = cameraService.initCamera();

        if(!tasksList.isEmpty() && demo != null){
            /*初始化模型*/
            ADetector aDetector = detectFeign.initDetector(1);

            Date excuteDate = new Date();// 获取当前时间
            // 加判断
            for(Integer taskId : tasksList){
                List<Record> list = new ArrayList<Record>();
                Task task = taskFeign.getTask(taskId);
                String taskname = task.getTaskName();
                String[] orders = task.getTaskTarget().split(",");
                List<String> image = new ArrayList<String>();
                int i = 0;
                String lastArmData = "";
                for (String order:orders) {

                    Device device = deviceFeign.getDeviceByDeviceId(Integer.parseInt(order));
                    String marker = device.getDeviceMarkerName();
                    String armData = xarmFeign.getPostureInstruction(Integer.parseInt(device.getPostureIds()));
                    boolean doArmMove = lastArmData.equals(armData) ;
                    System.out.println(String.valueOf(doArmMove));
                    lastArmData = armData;
                    image.add(singleMove(marker,armData,demo,excuteDate,i,doArmMove,taskname, aDetector));
                    i++;
                }
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyyMMddHHmm");
                Date date = new Date();// 获取当前时间
                String record_code = sdf.format(date);
                _log.warn("插入" + String.valueOf(image.size()));
                if (!image.isEmpty()){
                    int tag = 0;
                    for(String img:image) {
                        if(img == null){
                            break;
                        }
                        Record record = new Record();
                        record.setRecordCode(record_code);
                        record.setRecordTaskName(task.getTaskName());
                        record.setDeviceStatus("正常");
                        record.setImgPath(img);
                        record.setResult("");
                        list.add(record);
                        tag+=1;
                    }
                    image.clear();
                    recordFeign.batchInsertRecord(list);
                }
            }
        }
        boolean flag1 = demo.StopRealPlay();
        if (!flag1){
            _log.error("停止预览失败，错误代码：" + String.valueOf(HCNetSDK.INSTANCE.NET_DVR_GetLastError()));
        }
        boolean flag2 = demo.LogoutV30();
        if (!flag2){
            _log.error("注销失败，错误代码：" + String.valueOf(HCNetSDK.INSTANCE.NET_DVR_GetLastError()));
        }
        boolean flag3 = demo.CleanUp();
        if (!flag3){
            _log.error("释放sdk资源失败，错误代码：" + String.valueOf(HCNetSDK.INSTANCE.NET_DVR_GetLastError()));
        }
        robotControl("move","marker=POWER");
    }




    @SneakyThrows
    @Override
    public String singleMove(String order, String armData, ClientDemo demo, Date date,int i,boolean doArmMove,String taskname, ADetector aDetector) {
        String image = "";
        taskname = taskname.substring(0,3);
        String url = "http://192.168.10.10:9001/api/move";
        String img_path = "D:\\source\\";

        JsonObject result = new JsonObject();
        /*初始化时间*/
        String img_name = null;
        SimpleDateFormat sdfDate = new SimpleDateFormat();// 格式化日期
        SimpleDateFormat sdfTime = new SimpleDateFormat();// 格式化时间
        sdfDate.applyPattern("yyyyMMdd");
        sdfTime.applyPattern("HHmm");
        String imgDate = sdfDate.format(date);
        String imgTime = sdfTime.format(date);
        String savePath = "";

        /*发送底盘移动指令*/
        String data = HttpUtils.sendGet(url, "marker=" + order);
        JsonParser parser = new JsonParser();
        //json 转对象
        JsonObject object = (JsonObject) parser.parse(data);

        /*判断是否移动到指定位置*/
        while (true) {
            String robotStatus = robotStatus("move_status");
            if (robotStatus != "" && robotStatus != null && robotStatus.length() == 11) {
                robotStatus = robotStatus.substring(1, 10);
                if ("succeeded".equals(robotStatus)) {
                    if (doArmMove == false){
                        armMoveService.executeInstruction(armData);
                        boolean arm_flag = true;
                        while (true) {
                            boolean stop = armMoveService.getXarmStatus();

                            if (arm_flag == false && stop == true) {
                                _log.info("机械臂已移动至：" + order + "_" + String.valueOf(i));
                                savePath = img_path + imgDate + "\\" + imgTime + "\\" + taskname;

                                setDir(savePath);

                                Thread.sleep(2000);
                                boolean PIC = cameraService.SingleCapture(demo,savePath + "\\" + String.valueOf(i) + ".jpg");
                                if (!PIC) {
                                    int error = HCNetSDK.INSTANCE.NET_DVR_GetLastError();
                                    _log.error("检测出错，输入参数：" + savePath + "\\" + String.valueOf(i) + ".jpg");
                                    _log.error("检测出错，错误代码：" + String.valueOf(error));
                                }

                                long startTime = System.currentTimeMillis();
                                int c = 1;
                                while (System.currentTimeMillis() - startTime < 3000) {
                                    File capture = new File(savePath + "\\" + String.valueOf(i) + ".jpg");
                                    if (PIC && capture.exists()) {
                                        Thread.sleep(2000);
                                        result = detectFeign.detectImg(savePath + "\\" + String.valueOf(i) + ".jpg", aDetector);
                                        _log.info("检测出" + result);
                                        break;
                                    }
                                    c += 1;
                                }
                                _log.info("检测用时：" + String.valueOf(System.currentTimeMillis() - startTime));
                                File capture = new File(savePath + "\\" + String.valueOf(i) + ".jpg");
                                image = capture.exists() ? imgDate + "/" + imgTime + "/" + taskname + "/" + String.valueOf(i) + ".jpg" : "null";

                                break;
                            }
                            arm_flag = stop;
                        }
                        break;
                    }
                    else{
                        _log.info("机械臂已移动至：" + order + "_" + String.valueOf(i));
                        savePath = img_path + imgDate + "\\" + imgTime + "\\" + taskname;
                        //+ "\\" + instrumentName.split("-")[1] + ".jpg"
                        setDir(savePath);

                        Thread.sleep(3000);
                        boolean PIC = demo.SingleCapture(savePath + "\\" + String.valueOf(i) + ".jpg");
                        if (!PIC) {
                            int error = HCNetSDK.INSTANCE.NET_DVR_GetLastError();
                            _log.error("检测出错，输入参数：" + savePath + "\\" + String.valueOf(i) + ".jpg");
                            _log.error("检测出错，错误代码：" + String.valueOf(error));
                        }
                        long startTime = System.currentTimeMillis();
                        int c = 1;
                        while (System.currentTimeMillis() - startTime < 3000) {
                            File capture = new File(savePath + "\\" + String.valueOf(i) + ".jpg");
                            if (PIC && capture.exists()) {
                                Thread.sleep(2000);
                                result  = detectFeign.detectImg(savePath + "\\" + String.valueOf(i) + ".jpg", aDetector);
                                _log.info("检测出" + result);
                                break;
                            }
                            c += 1;
                        }
                        _log.info("检测用时：" + String.valueOf(System.currentTimeMillis() - startTime));
                        File capture = new File(savePath + "\\" + String.valueOf(i) + ".jpg");
                        image = capture.exists() ? imgDate + "/" + imgTime + "/" + taskname + "/" +String.valueOf(i) + ".jpg" : "null";
                        break;
                    }
                }
            }
        }
        image = recordFeign.updateImg(image);

        return image + "-" + result;
    }


    public static void setDir(String path){
        File savePath = new File(path);
        if(!savePath.exists()){
            savePath.mkdirs();
        }
    }


}
