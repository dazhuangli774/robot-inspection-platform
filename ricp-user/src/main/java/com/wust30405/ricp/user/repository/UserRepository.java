package com.wust30405.ricp.user.repository;

import com.wust30405.ricp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLoginName(String loginName);

    @Query("SELECT userType FROM User WHERE userId = :userId")
    String findUserTypeByUserId(Integer userId);
}
