package com.wust30405.ricp.record.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "http://localhost:8081/userInfo", name = "user")
public interface UserFeign {

    @RequestMapping(value = "/getUserType", method = RequestMethod.GET)
    String checkUserType(@RequestParam("userId") Integer userId);
}
