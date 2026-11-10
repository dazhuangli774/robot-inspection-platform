package com.wust30405.ricp.robot.feign;


import com.google.gson.JsonObject;
import com.wust30405.ricp.detect.factory.ADetector;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "http://localhost:8085/detect", name = "detect")
public interface DetectFeign {

    @RequestMapping(value = "/initDetector", method = RequestMethod.GET)
    ADetector initDetector(@RequestParam("detectorId") Integer detectorId);

    @RequestMapping(value = "/detectImg", method = RequestMethod.GET)
    JsonObject detectImg(@RequestParam("imgPath") String imgPath, @RequestParam("aDetector") ADetector aDetector);
}
