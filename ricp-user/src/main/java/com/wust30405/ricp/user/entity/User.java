package com.wust30405.ricp.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
    public void User(){}

    /*, columnDefinition = "用户ID"*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    /* COMMENT '登录账户'*/
    @Column(name = "login_name", columnDefinition = "varchar(30)", unique = true, nullable = false)
    private String loginName;

    /* COMMENT '用户类型（00系统用户 01注册用户）'*/
    @Column(name = "user_type", columnDefinition = "varchar(2)")
    private String userType;

    /* COMMENT '用户密码'*/
    @Column(name = "password", columnDefinition = "varchar(50)", nullable = false)
    private String password;

    /* COMMENT '加密盐'*/
    @Column(name = "salt", columnDefinition = "varchar(20)")
    private String salt;

    /* COMMENT '帐号状态（0正常 1停用）'*/
    @Column(name = "status", columnDefinition = "char(1)")
    private String status;

    /*, columnDefinition = "创建时间"*/
    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;


}
