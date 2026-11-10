package com.wust30405.ricp.task.service;

import com.wust30405.ricp.task.entity.Device;
import java.util.List;

public interface IDeviceService {
    List<Device> listAllDevice();

    Device insertDevice(Device device);

    void deleteDeviceById(Integer deviceId);

    Device selectDeviceById(Integer deviceId);

    void updateDevice(Device device);
}
