package com.wust30405.ricp.robot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@FeignClient(url = "http://localhost:8082/xarm", name = "xarm")
public interface XarmFeign {

    @RequestMapping(value = "/getPostureInstruction", method = RequestMethod.GET)
    String getPostureInstruction(@RequestParam("xarmId") Integer xarmId);

}
