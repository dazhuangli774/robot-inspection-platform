package com.wust30405.ricp.detect.controller;

import com.google.gson.JsonObject;
import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.detect.entity.Detector;
import com.wust30405.ricp.detect.factory.ADetector;
import com.wust30405.ricp.detect.feign.UserFeign;
import com.wust30405.ricp.detect.service.IDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("detect")
@RefreshScope
public class DetectorController {
    
    private final UserFeign userFeign;
    
    private final IDetectorService detectorService;

    public DetectorController(IDetectorService detectorService, UserFeign userFeign) {
        this.detectorService = detectorService;
        this.userFeign = userFeign;
    }


    @ResponseBody
    @GetMapping("checkUserType")
    public AjaxResult checkUserType(Integer userId) {
        String userType = userFeign.checkUserType(userId);
        if (userType.equals("01")) {
            return AjaxResult.success("success", true);
        }
        return AjaxResult.success("success", false);
    }

    @RequestMapping("/insertDetectorIndex")
    public String adddetector() {
        return "addDetector";
    }


    @RequestMapping("/insertDetector")
    public String adddetector(Detector detector) {
        detectorService.insertDetector(detector);
        return "detectorlist";
    }



    @GetMapping("/editDetector")
    public String editDetector(Integer detectorId, Model model) {
        Detector detectors = detectorService.selectDetectorById(detectorId);
        model.addAttribute("detectors",detectors);
        return "editDetector";
    }


    @RequestMapping("/updateDetector")
    public String updateDetector(Detector detector) {
        detectorService.updateDetector(detector);
        return "detectorlist";
    }



    @RequestMapping("/listAll")
    public String listAll(Model model) {
        List<Detector> detectors = detectorService.listAllDetector();
        model.addAttribute("detectors",detectors);
        return "detectorlist";
    }

    @RequestMapping("/listAllDetector")
    @ResponseBody
    public List listAllDetector(){
        return detectorService.listAllDetector();
    }

    @RequestMapping("/getDetectorsByDetectorId")
    @ResponseBody
    public List getDetectorsByDetectorId(List<Integer> detectorIdList) {
        List<Detector> detectorList = new ArrayList<>();
        for (Integer detectorId : detectorIdList) {
            detectorList.add(detectorService.selectDetectorById(detectorId));
        }
        return detectorList;
    }

    @RequestMapping("/getDetectorByDetectorId")
    @ResponseBody
    public Detector getDetectorByDetectorId(Integer detectorId) {
        return detectorService.selectDetectorById(detectorId);
    }

    @ResponseBody
    @RequestMapping(value = "/initDetector", method = RequestMethod.GET)
    ADetector initDetector(@RequestParam("detectorId") Integer detectorId) {
        return detectorService.initDetector(detectorId);
    }

    @ResponseBody
    @RequestMapping(value = "/detectImg", method = RequestMethod.GET)
    JsonObject detectImg(@RequestParam("imgPath") String imgPath, @RequestParam("aDetector") ADetector aDetector){
        return detectorService.detectImg(imgPath, aDetector);
    }

}
