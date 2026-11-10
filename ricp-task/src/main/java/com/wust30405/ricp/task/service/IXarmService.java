package com.wust30405.ricp.task.service;


import com.wust30405.ricp.task.entity.Xarm;

import java.util.List;

public interface IXarmService {
    List<Xarm> listAllXarm();

    Xarm insertXarm(Xarm xarm);

    void deleteXarmById(Integer postureId);

    Xarm selectXarmById(Integer postureId);

    void updateXarm(Xarm xarm);
}
