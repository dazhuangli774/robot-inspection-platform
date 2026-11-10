package com.wust30405.ricp.robot.controller;


import com.wust30405.ricp.robot.service.IRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("robot")
@RefreshScope
public class RobotControlController {

    private final IRobotService robotService;

    public RobotControlController(IRobotService robotService) {
        this.robotService = robotService;
    }


    @RequestMapping(value = "/performInspectionTasks", method = RequestMethod.GET)
    void performInspectionTasks(@RequestParam("taskList") List<Integer> taskIdList) {
        robotService.performInspectionTasks(taskIdList);
    }

    @RequestMapping(value = "/getRobotStatus", method = RequestMethod.GET)
    String getRobotStatus(){
        return robotService.robotStatusString();
    }
}
