package com.wust30405.ricp.user.service.impl;

import com.wust30405.ricp.user.entity.Role;
import com.wust30405.ricp.user.repository.RoleRepository;
import com.wust30405.ricp.user.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
