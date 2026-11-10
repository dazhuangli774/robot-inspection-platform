package com.wust30405.ricp.user.service.impl;

import com.wust30405.ricp.user.entity.PlatformService;
import com.wust30405.ricp.user.repository.PlatformServiceRepository;
import com.wust30405.ricp.user.service.IPlatformServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceServiceImpl implements IPlatformServiceService {

    private final PlatformServiceRepository platformServiceRepository;

    public PlatformServiceServiceImpl(PlatformServiceRepository platformServiceRepository) {
        this.platformServiceRepository = platformServiceRepository;
    }

    @Override
    public List<PlatformService> listAllService() {
        return platformServiceRepository.findAll();
    }

    @Override
    public PlatformService insertService(PlatformService platformService) {
        return platformServiceRepository.save(platformService);
    }

    @Override
    public void deleteServiceById(Integer serviceId) {
        platformServiceRepository.deleteServiceByServiceId(serviceId);
    }

    @Override
    public PlatformService selectServiceById(Integer serviceId) {
        return platformServiceRepository.findServiceByServiceId(serviceId);
    }

    @Override
    public void updateService(PlatformService platformService) {
        platformServiceRepository.save(platformService);
    }
}
