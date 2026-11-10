package com.wust30405.ricp.plan.quartz.job;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wust30405.ricp.plan.feign.RobotFeign;
import com.wust30405.ricp.plan.feign.TaskFeign;
import com.wust30405.ricp.plan.service.IPlanService;
import com.wust30405.ricp.task.entity.Task;
import org.apache.commons.beanutils.ConvertUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class executeJob extends BaseJob {

    private static Logger _log = LoggerFactory.getLogger(executeJob.class);

    @Autowired
    private IPlanService planService;


    @Autowired
    private RobotFeign robotFeign;
    public executeJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        Integer planId = Integer.parseInt(key.getName());
        try{

            _log.warn("空闲时间时间: " + new Date());

            //判断机器人是否可以开始巡检
            boolean status=ifCanInspection();

            if (status){
                _log.info("巡检执行时间: " + new Date());

                String[] orderList = planService.selectPlanById(planId).getExecuteTasks().split(",");
                Integer[] taskArray = (Integer[]) ConvertUtils.convert(orderList, Integer.class);
                robotFeign.performInspectionTasks(Arrays.asList(taskArray));

            } else{
                _log.info("不满足巡检条件，跳过巡检");
            }

        }catch (Exception e){
            String currentTime=getCurrentTime();
            _log.error("执行任务出错了,执行时间："+currentTime);
            _log.error(""+e);
            JobExecutionException e2=new JobExecutionException(e);
            throw e2;

        }

    }



    public String getCurrentTime(){
        Date excuteDate = new Date();// 获取当前时间

        SimpleDateFormat sdfDate = new SimpleDateFormat();// 格式化日期

        sdfDate.applyPattern("yyyyMMddHH");

        String imgDate = sdfDate.format(excuteDate);
        return imgDate;

    }

    public boolean ifCanInspection(){

        try{
            String results = robotFeign.getRobotStatus();
            //成功获取到机器人的状态信息
            if(results==null) {
                return false;
            }
            JsonParser parser = new JsonParser();

            JsonObject resultObject = (JsonObject)parser.parse(results);
            //电量
            String powerPercent=resultObject.get("power_percent").toString();
            //移动目标点位
            String moveTarget=resultObject.get("move_target").toString();
            int length=moveTarget.length();
            //去掉首尾的"
            moveTarget=moveTarget.substring(1,length-1);


            //移动是否成功
            String moveStatus=resultObject.get("move_status").toString();
            length=moveStatus.length();
            //去掉首尾的"
            moveStatus=moveStatus.substring(1,length-1);

            _log.info("机器人当前电量："+powerPercent+" ,最后的移动目标点位："+moveTarget+" ,最后的移动状态："+moveStatus);

            if(Integer.parseInt(powerPercent) < 50){
                return false;
            }

            if(!"POWER".equals(moveTarget)){
                return false;
            }

            if("POWER".equals(moveTarget)&&!"succeeded".equals(moveStatus)){
                return false;
            }

            return true;
        }catch (Exception e){
            _log.info("定点启动前,获取状态失败");
            e.printStackTrace();
            return false;
        }

    }

}
