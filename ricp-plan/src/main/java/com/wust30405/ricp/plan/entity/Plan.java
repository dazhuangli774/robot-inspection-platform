package com.wust30405.ricp.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inspection_plan")
@Data
public class Plan {
    public void Plan(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;


    @Column(name = "plan_name")
    private String planName;


    @Column(name = "plan_des")
    private String planDes;


    @Column(name = "execute_tasks")
    private String executeTasks;


    @Column(name = "status", columnDefinition = "char(1)")
    private String status;


    @Column(name = "execute_time")
    private String executeTime;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;


    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}
