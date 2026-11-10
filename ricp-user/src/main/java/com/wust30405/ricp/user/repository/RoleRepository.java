package com.wust30405.ricp.user.repository;

import com.wust30405.ricp.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override
    List<Role> findAll();


}
