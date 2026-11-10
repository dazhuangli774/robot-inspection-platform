package com.wust30405.ricp.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscribe")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Subscribe {
    public void Subscribe(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscribe_id")
    private Integer subscribeId;

    @Column(name = "subscribe_name")
    private String subscribeName;

    @Column(name = "subscribe_des")
    private String subscribeDes;

    @Column(name = "subscriber")
    private Integer subscriber;

    @Column(name = "subscribe_duration")
    private Integer subscribeDuration;

    @Column(name = "subscribe_service")
    private Integer subscribeService;

    @Column(name = "subscribe_cost")
    private String subscribeCost;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date billCreateTime;
}
