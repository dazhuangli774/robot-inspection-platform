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
@Table(name = "inspection_task")
@Data
public class Task {

    public void Task(){}

    /*, columnDefinition = "任务ID"*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    /*, columnDefinition = "任务名称"*/
    @Column(name = "task_name")
    private String taskName;

    /*, columnDefinition = "任务描述"*/
    @Column(name = "task_des")
    private String taskDes;

    /*, columnDefinition = "任务目标"*/
    @Column(name = "task_target")
    private String taskTarget;

    /* COMMENT '任务状态（0为未执行，1为正在执行）'*/
    @Column(name = "status", columnDefinition = "char(1)")
    private String status;

    /*, columnDefinition = "创建人"*/
//    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    /*, columnDefinition = "创建时间"*/
    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

}
