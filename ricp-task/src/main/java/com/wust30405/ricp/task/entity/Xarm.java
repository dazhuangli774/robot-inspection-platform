package com.wust30405.ricp.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inspection_xarm")
@Data
public class Xarm {
    public void Xarm(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posture_id")
    private Integer postureId;

    @Column(name = "posture_des")
    private String postureDes;

    @Column(name = "posture_instruction")
    private String postureInstruction;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

}
