package com.wust30405.ricp.record.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inspection_record")
@Data
public class Record {
    public void Record(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "record_code")
    private String recordCode;

    @Column(name = "record_task_name")
    private String recordTaskName;

    @Column(name = "result")
    private String result;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "device_status")
    private String deviceStatus;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;


}
