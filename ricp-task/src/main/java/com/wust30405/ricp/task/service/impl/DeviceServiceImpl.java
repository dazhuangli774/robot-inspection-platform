package com.wust30405.ricp.task.service.impl;

import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.repository.DeviceRepository;
import com.wust30405.ricp.task.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> listAllDevice() {
        return deviceRepository.findAll();
    }

    @Override
    public Device insertDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDeviceById(Integer deviceId) {
        deviceRepository.deleteDeviceByDeviceId(deviceId);
    }

    @Override
    public Device selectDeviceById(Integer deviceId) {
        return deviceRepository.findDeviceByDeviceId(deviceId);
    }

    @Override
    public void updateDevice(Device device) {
        deviceRepository.save(device);
    }
}
