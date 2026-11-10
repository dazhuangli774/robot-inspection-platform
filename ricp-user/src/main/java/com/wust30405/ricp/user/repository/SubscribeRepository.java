package com.wust30405.ricp.user.repository;

import com.wust30405.ricp.user.entity.Subscribe;
import com.wust30405.ricp.user.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.net.Inet4Address;
import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Override
    List<Subscribe> findAll();

    @Transactional
    @Modifying
    void deleteSubscribeBySubscribeId(Integer subscribeId);

    Subscribe findSubscribeBySubscribeId(Integer subscribeId);

    Subscribe findBySubscriber(Integer subscriber);
}
