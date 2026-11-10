package com.wust30405.ricp.task.controller;

import com.wust30405.ricp.task.entity.Device;
import com.wust30405.ricp.task.entity.Task;
import com.wust30405.ricp.task.service.IDeviceService;
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
@RequestMapping("device")
@RefreshScope
public class DeviceController {
    
    private final IDeviceService deviceService;
    
    public DeviceController(IDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping("/insertDeviceIndex")
    public String addtask() {
        return "adddevice";
    }

    @RequestMapping("/insertDevice")
    public String addDevice(Device device) {
        deviceService.insertDevice(device);
        return "devicelist";
    }


    @GetMapping("/deleteDevice")
    public String deleteDevice(Integer taskId){
        deviceService.deleteDeviceById(taskId);
        return "redirect:listAllDeviceIndex";
    }


    @GetMapping("/editDevice")
    public String editDevice(Integer taskId, Model model) {
        Device devices = deviceService.selectDeviceById(taskId);
        model.addAttribute("tasks",devices);
        return "editDevice";
    }


    @RequestMapping("/updateDevice")
    public String updateDevice(Device device) {
        deviceService.updateDevice(device);
        return "devicelist";
    }



    @RequestMapping("/listAllDeviceIndex")
    public String listAllDeviceIndex(Model model) {
        List<Device> devices = deviceService.listAllDevice();
        model.addAttribute("tasks",devices);
        return "devicelist";
    }

    @RequestMapping("/listAllDevice")
    @ResponseBody
    public List listAllDevice(){
        return deviceService.listAllDevice();
    }
}
