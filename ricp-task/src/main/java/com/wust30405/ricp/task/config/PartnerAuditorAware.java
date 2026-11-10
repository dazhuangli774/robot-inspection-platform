//package com.wust30405.ricp.config;
//
//
//import com.wust30405.ricp.task.feign.UserFeign;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.security.Principal;
//import java.util.Optional;
//
//@Configuration
//public class PartnerAuditorAware implements AuditorAware<String> {
//
//    private final UserFeign userFeign;
//
//    public PartnerAuditorAware(UserFeign userFeign) {
//        this.userFeign = userFeign;
//    }
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//
//        String user = userFeign.getLoginUser();
//        if (user != null) {
//            return Optional.of(user);
//        }
//        return Optional.empty();
//    }
//}
