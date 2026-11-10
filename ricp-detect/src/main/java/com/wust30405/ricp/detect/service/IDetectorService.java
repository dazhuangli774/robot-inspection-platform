package com.wust30405.ricp.detect.service;


import com.google.gson.JsonObject;
import com.wust30405.ricp.detect.entity.Detector;
import com.wust30405.ricp.detect.factory.ADetector;

import java.util.List;


public interface IDetectorService {

    Detector insertDetector(Detector detector);

    Detector findDetectorByDetectorId(Integer detectorId);


    ADetector initDetector(Integer detectorId);

    JsonObject detectImg(String imgPath, ADetector aDetector);

    List<Detector> listAllDetector();


    /*删除任务*/
    void deleteDetectorById(Integer detectorId);

    /*编辑任务*/
    Detector selectDetectorById(Integer detectorId);

    /*更新*/
    void updateDetector(Detector detector);

}
