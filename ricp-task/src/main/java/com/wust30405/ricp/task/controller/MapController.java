package com.wust30405.ricp.task.controller;

import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.entity.Map;
import com.wust30405.ricp.task.service.IMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("map")
@RefreshScope
public class MapController {

    private final IMapService mapService;

    public MapController(IMapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping("/insertMapIndex")
    public String addtask() {
        return "adddevice";
    }

    @RequestMapping("/insertMap")
    public String addMap(Map map) {
        mapService.insertMap(map);
        return "maplist";
    }


    @GetMapping("/deleteMap")
    public String deleteMap(Integer taskId){
        mapService.deleteMapById(taskId);
        return "redirect:listAllMapIndex";
    }


    @GetMapping("/editMap")
    public String editMap(Integer taskId, Model model) {
        Map maps = mapService.selectMapById(taskId);
        model.addAttribute("tasks",maps);
        return "editMap";
    }

    @RequestMapping("/updateMap")
    public String updateMap(Map map) {
        mapService.updateMap(map);
        return "maplist";
    }



    @RequestMapping("/listAllMapIndex")
    public String listAllMapIndex(Model model) {
        List<Map> maps = mapService.listAllMap();
        model.addAttribute("tasks",maps);
        return "maplist";
    }

    @RequestMapping("/listAllMap")
    @ResponseBody
    public List listAllMap(){
        return mapService.listAllMap();
    }
}
