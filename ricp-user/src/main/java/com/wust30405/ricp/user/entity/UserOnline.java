package com.wust30405.ricp.user.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_online")
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserOnline {
    public void UserOnline(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    /* COMMENT '登录账户'*/
    @Column(name = "login_name", columnDefinition = "varchar(30)", unique = true, nullable = false)
    private String loginName;

}
