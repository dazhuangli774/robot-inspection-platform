package com.wust30405.ricp.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "role")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Role {
    public void Role(){}

    /*, columnDefinition = "角色ID"*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    /*, columnDefinition = "角色名称"*/
    @Column(name = "role_name")
    private String roleName;

    /*, columnDefinition = "角色权限字符"*/
    @Column(name = "role_key")
    private String role_key;

    /* COMMENT '角色状态'*/
    @Column(name = "status", columnDefinition = "char(1)")
    private String status;

    /*, columnDefinition = "创建时间"*/
    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date billCreateTime;
}
