package com.wust30405.ricp.task.repository;

import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Override
    List<Device> findAll();

    @Transactional
    @Modifying
    void deleteDeviceByDeviceId(Integer deviceId);

    Device findDeviceByDeviceId(Integer deviceId);

}
