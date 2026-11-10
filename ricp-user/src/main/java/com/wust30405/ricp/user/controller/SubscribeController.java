package com.wust30405.ricp.user.controller;

import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.user.entity.Subscribe;
import com.wust30405.ricp.user.service.ISubscribeService;
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
@RequestMapping("subscribe")
@RefreshScope
public class SubscribeController {

    private final IUserService userService;
    private final ISubscribeService subscribeService;

    public SubscribeController(IUserService userService, ISubscribeService subscribeService) {
        this.userService = userService;
        this.subscribeService = subscribeService;
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

    @RequestMapping("/insertSubscribeIndex")
    public String addSubscribe() {
        return "addSubscribe";
    }


    @RequestMapping("/insertSubscribe")
    public String addsubscribe(Subscribe subscribe) {
        subscribeService.insertSubscribe(subscribe);
        return "planlist";
    }


    @GetMapping("/deleteSubscribe")
    public String deleteSubscribe(Integer subscribeId){
        subscribeService.deleteSubscribeById(subscribeId);
        return "redirect:listAll";
    }


    @GetMapping("/editSubscribe")
    public String editSubscribe(Integer subscribeId, Model model) {
        Subscribe subscribes = subscribeService.selectSubscribeById(subscribeId);
        model.addAttribute("subscribes",subscribes);
        return "editSubscribe";
    }


    @RequestMapping("/updateSubscribe")
    public String updateSubscribe(Subscribe subscribe) {
        subscribeService.updateSubscribe(subscribe);
        return "subscribeList";
    }



    @RequestMapping("/listAll")
    public String listAll(Model model) {
        List<Subscribe> subscribes = subscribeService.listAllSubscribe();
        model.addAttribute("subscribes",subscribes);
        return "subscribeList";
    }

    @RequestMapping("/listAllSubscribe")
    @ResponseBody
    public List listAllSubscribe(){
        return subscribeService.listAllSubscribe();
    }


    @RequestMapping("/getSubscribeBySubscribeId")
    @ResponseBody
    public Subscribe getSubscribeBySubscribeId(Integer subscribeId) {
        return subscribeService.selectSubscribeById(subscribeId);
    }
}
