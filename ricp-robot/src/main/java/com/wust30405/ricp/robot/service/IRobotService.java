package com.wust30405.ricp.robot.service;


import ClientDemo.ClientDemo;
import com.google.gson.JsonObject;
import com.wust30405.ricp.detect.factory.ADetector;

import java.util.Date;
import java.util.List;

public interface IRobotService {

    /*传输命令*/
    JsonObject robotControl(String order, String param);

    /*机器人全局状态*/
    String overallRobotStatus();

    /*机器人指定状态*/
    String robotStatus(String statusName);

    //机器人的电池状态
    String robotStatusString();



    /*软急停*/
    String setEstop(String flag);

    void performInspectionTasks(List<Integer> tasksList);

    String singleMove(String order, String armData, ClientDemo demo, Date date, int i, boolean doArmMove, String taskname, ADetector aDetector);

}
