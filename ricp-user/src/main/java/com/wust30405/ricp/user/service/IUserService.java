package com.wust30405.ricp.user.service;

import com.wust30405.ricp.user.entity.User;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Yxw
 * @Date 2022/4/11 13:55
 */
public interface IUserService {
    User selectUserByLoginName(String loginName);
    void insertUser(User user);
    String checkUserType(Integer userId);
    User checkUser(String username, String password);
}
