package com.wust30405.ricp.detect.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inspection_detector")
@Data
public class Detector {

    public void Detector(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detector_id")
    private Integer detectorId;

    @Column(name = "detector_name")
    private String detectorName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "detector_netfile")
    private String detectorNetFile;

    @Column(name = "detector_weight")
    private String detectorWeight;
}
