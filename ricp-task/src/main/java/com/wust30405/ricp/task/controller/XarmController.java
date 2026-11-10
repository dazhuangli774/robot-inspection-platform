package com.wust30405.ricp.task.controller;

import ClientDemo.ClientDemo;
import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.entity.Xarm;
import com.wust30405.ricp.task.feign.ArmControlFegin;
import com.wust30405.ricp.task.service.IXarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("xram")
@RefreshScope
public class XarmController {

    private final ArmControlFegin armControlFegin;

    private final IXarmService xarmService;

    public XarmController(IXarmService xarmService, ArmControlFegin armControlFegin) {
        this.xarmService = xarmService;
        this.armControlFegin = armControlFegin;
    }


    @RequestMapping("/insertXarmIndex")
    public String addtask() {
        return "addxarm";
    }

    @RequestMapping("/insertXarm")
    public String addXarm(Xarm xarm) {
//        xarm.setPostureInstruction(armControlFegin.getInstruction());
        xarmService.insertXarm(xarm);
        return "xarmlist";
    }


    @GetMapping("/deleteXarm")
    public String deleteXarm(Integer taskId){
        xarmService.deleteXarmById(taskId);
        return "redirect:listAllXarmIndex";
    }


    @GetMapping("/editXarm")
    public String editXarm(Integer taskId, Model model) {
        Xarm xarms = xarmService.selectXarmById(taskId);
        model.addAttribute("tasks",xarms);
        return "editXarm";
    }


    @RequestMapping("/updateXarm")
    public String updateXarm(Xarm xarm) {
        xarmService.updateXarm(xarm);
        return "xarmlist";
    }



    @RequestMapping("/listAllXarmIndex")
    public String listAllXarmIndex(Model model) {
        List<Xarm> xarms = xarmService.listAllXarm();
        model.addAttribute("tasks",xarms);
        return "xarmlist";
    }

    @RequestMapping("/listAllXarm")
    @ResponseBody
    public List listAllXarm(){
        return xarmService.listAllXarm();
    }

    @RequestMapping("/getPostureInstruction")
    @ResponseBody
    public String getPostureInstruction(Integer xarmId){
        return xarmService.selectXarmById(xarmId).getPostureInstruction();
    }
}
