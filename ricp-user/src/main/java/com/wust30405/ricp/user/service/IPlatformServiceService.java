package com.wust30405.ricp.user.service;


import com.wust30405.ricp.user.entity.PlatformService;

import java.util.List;

public interface IPlatformServiceService {

    List<PlatformService> listAllService();

    PlatformService insertService(PlatformService platformService);

    void deleteServiceById(Integer serviceId);

    PlatformService selectServiceById(Integer serviceId);

    void updateService(PlatformService platformService);
}
