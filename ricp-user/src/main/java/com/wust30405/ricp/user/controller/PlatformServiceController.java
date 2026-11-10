package com.wust30405.ricp.user.controller;

import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.user.entity.PlatformService;
import com.wust30405.ricp.user.service.IPlatformServiceService;
import com.wust30405.ricp.user.service.IUserService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("service")
@RefreshScope
public class PlatformServiceController {

    private final IUserService userService;

    private final IPlatformServiceService platformServiceService;

    public PlatformServiceController(IUserService userService, IPlatformServiceService platformServiceService) {
        this.userService = userService;
        this.platformServiceService = platformServiceService;
    }

    @ResponseBody
    @GetMapping("checkUserType")
    public AjaxResult checkUserType(Integer userId) {
        String userType = userService.checkUserType(userId);
        if (userType.equals("01")) {
            return AjaxResult.success("success", true);
        }
        return AjaxResult.success("success", false);
    }

    @RequestMapping("/insertServiceIndex")
    public String addService() {
        return "addService";
    }


    @RequestMapping("/insertService")
    public String addtask(PlatformService platformService) {
        platformServiceService.insertService(platformService);
        return "servicelist";
    }


    @GetMapping("/deleteService")
    public String deleteService(Integer serviceId){
        platformServiceService.deleteServiceById(serviceId);
        return "redirect:listAll";
    }


    @GetMapping("/editPlatformServic")
    public String editService(Integer serviceId, Model model) {
        PlatformService platformService = platformServiceService.selectServiceById(serviceId);
        model.addAttribute("services",platformService);
        return "editService";
    }


    @RequestMapping("/updateService")
    public String updateService(PlatformService platformService) {
        platformServiceService.updateService(platformService);
        return "servicelist";
    }



    @RequestMapping("/listAll")
    public String listAll(Model model) {
        List<PlatformService> service = platformServiceService.listAllService();;
        model.addAttribute("services",service);
        return "servicelist";
    }

    @RequestMapping("/listAllService")
    @ResponseBody
    public List listAllService(){
        return platformServiceService.listAllService();
    }



    @RequestMapping("/getServiceByServiceId")
    @ResponseBody
    public PlatformService getServiceByServiceId(Integer serviceId) {
        return platformServiceService.selectServiceById(serviceId);
    }
}
