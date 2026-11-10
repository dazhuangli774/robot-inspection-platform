package com.wust30405.ricp.user.service.impl;

import com.wust30405.ricp.user.entity.User;
import com.wust30405.ricp.user.repository.UserRepository;
import com.wust30405.ricp.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User selectUserByLoginName(String loginName) {
        return userRepository.findUserByLoginName(loginName);
    }

    @Override
    public void insertUser(User user) {
        user.setUserType("01");
        user.setSalt(user.getLoginName()+user.getPassword());
        user.setStatus("0");
        userRepository.save(user);
    }

    @Override
    public String checkUserType(Integer userId) {
        return userRepository.findUserTypeByUserId(userId);
    }

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findUserByLoginName(username);

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
