package com.wust30405.ricp.detect.repository;

import com.wust30405.ricp.detect.entity.Detector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DetectorRepository extends JpaRepository<Detector, Integer> {

    Detector findDetectorByDetectorId(Integer detectorId);
    @Override
    List<Detector> findAll();

    @Transactional
    @Modifying
    void deleteDetectorByDetectorId(Integer detectorId);
    
}
