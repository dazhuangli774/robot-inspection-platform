package com.wust30405.ricp.robot.controller;

import com.wust30405.ricp.robot.service.impl.ArmMoveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("armControl")
@RefreshScope
public class ArmMoveController {

    private final ArmMoveServiceImpl armMoveService;

    public ArmMoveController(ArmMoveServiceImpl armMoveService) {
        this.armMoveService = armMoveService;
    }

    @RequestMapping("getInstruction")
    public String getInstruction(){
        return armMoveService.getInstruction();
    }


}
