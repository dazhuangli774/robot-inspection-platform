package com.wust30405.ricp.user.service;

import com.wust30405.ricp.user.entity.Role;

import java.util.List;


public interface IRoleService {
    List<Role> findAllRole();

    void addRole(Role role);
}
