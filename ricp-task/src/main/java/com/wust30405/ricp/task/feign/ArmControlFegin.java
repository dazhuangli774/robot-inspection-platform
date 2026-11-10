package com.wust30405.ricp.task.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "http://localhost:8084/armControl", name = "robot")
public interface ArmControlFegin {

    @RequestMapping(value = "/getInstruction", method = RequestMethod.GET)
    String getInstruction();
}
