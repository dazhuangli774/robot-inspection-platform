package com.wust30405.ricp.user.controller;


import com.wust30405.ricp.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("userInfo")
@RefreshScope
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUserType")
    public String getUserType(Integer userId) {
        return userService.checkUserType(userId);
    }

    @PostMapping("getUserSession")
    public String getUserSession(HttpSession session) {
            System.out.println(session.getAttribute("loginUser") + "  ID: " + session.getId());
        return (String) session.getAttribute("loginUser");
    }

    @RequestMapping("getSession")
    public String getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("loginUser") + "  ID: " + session.getId());
        return (String) request.getSession().getAttribute("loginUser");
    }
}
