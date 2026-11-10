package com.wust30405.ricp.task.service;


import com.alibaba.fastjson.JSONObject;
import com.wust30405.ricp.task.entity.Map;

import java.util.List;

public interface IMapService {
    List<Map> listAllMap();

    Map insertMap(Map map);

    void deleteMapById(Integer mapId);

    Map selectMapById(Integer mapId);

    void updateMap(Map map);

    Map getMap(String mapName);
}
