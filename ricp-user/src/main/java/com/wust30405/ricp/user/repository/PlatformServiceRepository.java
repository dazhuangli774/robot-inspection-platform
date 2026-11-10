package com.wust30405.ricp.user.repository;

import com.wust30405.ricp.user.entity.PlatformService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlatformServiceRepository extends JpaRepository<PlatformService, Integer> {
    @Override
    List<PlatformService> findAll();

    @Transactional
    @Modifying
    void deleteServiceByServiceId(Integer serviceId);

    PlatformService findServiceByServiceId(Integer serviceId);

}
