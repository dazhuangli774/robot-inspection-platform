package com.wust30405.ricp.robot.service.impl;

import com.wust30405.ricp.robot.feign.XarmFeign;
import com.wust30405.ricp.robot.service.IArmMoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static com.wust30405.ricp.robot.utils.InstructionUtil.BinaryToHexString;
import static com.wust30405.ricp.robot.utils.InstructionUtil.hexStrToBinaryStr;

@Service
public class ArmMoveServiceImpl implements IArmMoveService {
    private static Logger _log = LoggerFactory.getLogger(ArmMoveServiceImpl.class);

    @Autowired
    private XarmFeign xarmFeign;


    @Override
    public String getInstruction() {
        String cmdInfor="00 01 00 02 00 01 29";
        String strReturn = sendOrder(cmdInfor);
        return strReturn;
    }


    @Override
    public void executeInstructionById(Integer xarmId) {
        String instruction = xarmFeign.getPostureInstruction(xarmId);
        String cmdInfor = instruction;
        System.out.println(cmdInfor);
        sendOrder(cmdInfor);
    }

    @Override
    public void executeInstruction(String armData) {
        String strReturn=sendOrder(armData);

        if(strReturn!=null){
            _log.info("发送机械臂移动指令成功");
        }

        int i=0;
        while(strReturn==null){
            strReturn=sendOrder(armData);
            ++i;
            _log.info("发送机械臂移动指令失败,已重发发送"+i+"次");
            if(i>10){
                _log.info("发送机械臂移动指令失败,已重发发送10次后,还是失败,指令为："+armData);
                break;
            }
        }
    }

    @Override
    public boolean getXarmStatus() {
        boolean flag = false;
        String instruction = "00 01 00 02 00 01 0D";
        String strReturn = sendOrder(instruction);

        if(strReturn!=null){
            _log.info("机械臂响应指令："+strReturn.substring(25,26));
        }else{
            _log.info("机械臂响应指令：指令为空");
        }

        if(strReturn!=null&&strReturn.substring(25,26).equals("2")){
            flag = true;
        }
        return flag;
    }

    @Override
    public void backToZeroPoint(){
        boolean flag = true;
        sendOrder("00 01 00 02 00 0D 19 9B C4 A0 3E F3 66 DF 40 00 00 00 00");
        while (true){
            boolean stop = getXarmStatus();
            if(flag  == false && stop == true){
                _log.info("机械臂已回到零点继续移动");
                break;
            }
            flag  = stop;
        }
    }

    public String sendOrder(String order){
        String cmdInfor= order;
        String strReturn = null;
        Socket clientSocket=null;
        InputStream is=null;
        PrintWriter pw=null;
        OutputStream os=null;
        try {
            //要连接的服务端IP地址
            String host = "192.168.10.205";
            //要连接的服务端对应的监听端口
            int port = 502;
            //将十六进制的字符串转换成字节数组
            byte[] cmdInfor2 = hexStrToBinaryStr(cmdInfor);

            //1.建立客户端socket连接，指定服务器位置及端口
            clientSocket =new Socket(host,port);

            //2.得到socket读写流
            os=clientSocket.getOutputStream();
            pw=new PrintWriter(os);
            //输入流
            is=clientSocket.getInputStream();

            //3.利用流按照一定的操作，对socket进行读写操作
            os.write(cmdInfor2);
            os.flush();
            clientSocket.shutdownOutput();

            //接收服务器的响应
            int line = 0;
//            System.out.println(is.available());
            byte[] buf = new byte[1024];
            //接收收到的数据
            int len=is.read(buf);

            strReturn = BinaryToHexString(buf);
            //4.关闭资源
            is.close();
            pw.close();
            os.close();
            clientSocket.close();
        }catch (Exception e){
            _log.info("发生机械臂指令失败,指令为：{}",order);
            _log.info("发生机械臂指令失败，异常信息为：{}",e);
//            e.printStackTrace();
        }finally {
            if(clientSocket!=null){
                try {
                    is.close();
                    pw.close();
                    os.close();
                    clientSocket.close();
                } catch (IOException e) {
                    _log.info("发生机械臂指令失败后,关闭资源失败为：{}",order);
                }

            }
        }
        return strReturn;
    }
}
