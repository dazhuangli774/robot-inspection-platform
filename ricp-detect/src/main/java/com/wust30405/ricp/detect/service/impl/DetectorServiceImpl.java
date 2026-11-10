package com.wust30405.ricp.detect.service.impl;

import com.google.gson.JsonObject;
import com.sun.jna.Native;
import com.wust30405.ricp.detect.entity.Detector;
import com.wust30405.ricp.detect.factory.ADetector;
import com.wust30405.ricp.detect.factory.DetectorFactory;
import com.wust30405.ricp.detect.repository.DetectorRepository;
import com.wust30405.ricp.detect.service.IDetectorService;
import com.wust30405.ricp.detect.utils.Box;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectorServiceImpl implements IDetectorService {

    private final DetectorRepository detectorRepository;

    public DetectorServiceImpl(DetectorRepository detectorRepository) {
        this.detectorRepository = detectorRepository;
    }

    @Override
    public Detector insertDetector(Detector detector) {
        return detectorRepository.save(detector);
    }


    @Override
    public Detector findDetectorByDetectorId(Integer detectorId) {
        return detectorRepository.findDetectorByDetectorId(detectorId);
    }



    @Override
    public ADetector initDetector(Integer detectorId) {

        Detector detector = detectorRepository.findDetectorByDetectorId(detectorId);
        String netCfg = detector.getDetectorNetFile();
        String weight = detector.getDetectorWeight();
        String className = detector.getClassName();
        ADetector aDetector = DetectorFactory.getClassByName(className);
        int c = aDetector.initDetector(netCfg, weight, 1);
        return aDetector;
    }

    @Override
    public JsonObject detectImg(String imgPath, ADetector aDetector) {

        Box[] boxtt = new Box[1000];
        for (int k = 0; k < boxtt.length; k++) {
            boxtt[k].initBox(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
        }
        return aDetector.detectImage(imgPath, boxtt);
    }

    @Override
    public List<Detector> listAllDetector() {
        return detectorRepository.findAll();
    }

    @Override
    public void deleteDetectorById(Integer detectorId) {
        detectorRepository.deleteDetectorByDetectorId(detectorId);
    }

    @Override
    public Detector selectDetectorById(Integer detectorId) {
        return detectorRepository.findDetectorByDetectorId(detectorId);
    }

    @Override
    public void updateDetector(Detector detector) {
        detectorRepository.save(detector);
    }
}
