package com.wust30405.ricp.task.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wust30405.ricp.common.util.HttpUtils;
import com.wust30405.ricp.task.entity.Map;
import com.wust30405.ricp.task.repository.MapRepository;
import com.wust30405.ricp.task.service.IMapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements IMapService {

    private final MapRepository mapRepository;

    public MapServiceImpl(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    private static String ROBOT_URL = " http://192.168.10.10:9001/api/";

    @Override
    public List<Map> listAllMap() {
        return mapRepository.findAll();
    }

    @Override
    public Map insertMap(Map map) {
        return mapRepository.save(map);
    }

    @Override
    public void deleteMapById(Integer mapId) {
        mapRepository.deleteMapByMapId(mapId);
    }

    @Override
    public Map selectMapById(Integer mapId) {
        return mapRepository.findMapByMapId(mapId);
    }

    @Override
    public void updateMap(Map map) {
        mapRepository.save(map);
    }

    @Override
    public Map getMap(String mapName) {
        String data = HttpUtils.sendGet(ROBOT_URL, "/markers/query_list");

        JsonParser parser = new JsonParser();

        JsonObject object = new JsonObject();
        if(!"".equals(data)){
            object=(JsonObject) parser.parse(data);
        }
        Map map = new Map();
        map.setMapName(mapName);
        map.setMapCoordinate(object.get("result").getAsString());
        map.setMapSavedId(mapName);
        return map;
    }
}
