package com.wust30405.ricp.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8082/device", name = "device")
public interface DeviceFeign {
    @RequestMapping(value = "/listAllDevice", method = RequestMethod.GET)
    List listAllDevice();
}
