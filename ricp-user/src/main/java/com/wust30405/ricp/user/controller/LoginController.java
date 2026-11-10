package com.wust30405.ricp.user.controller;

import com.wust30405.ricp.common.config.Global;
import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.common.util.ServletUtils;
import com.wust30405.ricp.common.util.StringUtils;
import com.wust30405.ricp.user.entity.User;
import com.wust30405.ricp.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.wust30405.ricp.common.util.AjaxResult.error;
import static com.wust30405.ricp.common.util.AjaxResult.success;

@Controller
@RequestMapping("/user")
public class LoginController {

    private final IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        mmap.put("name", Global.getName());
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("version", Global.getVersion());

        return "login";
    }

    @PostMapping("/Login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login")

    public AjaxResult login(String username, String password, HttpSession session) {
        User user = userService.checkUser(username,password);
        if (user != null) {

            session.setAttribute("loginUser", user.getLoginName());
            System.out.println(session.getAttribute("loginUser") + "  ID: " + session.getId());

            return AjaxResult.success();
        }
        return AjaxResult.error("用户名或密码错误");
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/user/login";
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
