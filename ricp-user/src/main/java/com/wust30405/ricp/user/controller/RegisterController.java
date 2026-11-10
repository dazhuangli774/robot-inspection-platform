package com.wust30405.ricp.user.controller;

import com.wust30405.ricp.common.config.Global;
import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.user.entity.User;
import com.wust30405.ricp.user.service.IUserService;
import com.wust30405.ricp.common.*;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.wust30405.ricp.common.util.AjaxResult.error;
import static com.wust30405.ricp.common.util.AjaxResult.success;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private static Logger _log = LoggerFactory.getLogger(RegisterController.class);

    private final IUserService userService;

    public RegisterController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerIndex")
    public String register(ModelMap mmap) {

        mmap.put("name", Global.getName());
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("version", Global.getVersion());

        return "register";
    }


    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(User user) {
        try{
            userService.insertUser(user);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            _log.error(cause.toString());
            if (cause instanceof ConstraintViolationException) {
                String exception = ((ConstraintViolationException) cause).getSQLState();
                if (exception.equals("23000")) {
                    return AjaxResult.error("用户名已存在！");
                }
                else {
                    _log.error(e.getMessage());
                    return AjaxResult.error(e.getMessage());
                }
            } else {
                _log.error(e.getMessage());
                return AjaxResult.error(e.getMessage());
            }
        }
        return AjaxResult.success();
    }
}
