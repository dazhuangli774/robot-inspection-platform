package com.wust30405.ricp.user.service.impl;

import com.wust30405.ricp.user.entity.Subscribe;
import com.wust30405.ricp.user.repository.SubscribeRepository;
import com.wust30405.ricp.user.service.ISubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeServiceImpl implements ISubscribeService {

    private final SubscribeRepository subscribeRepository;

    public SubscribeServiceImpl(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public List<Subscribe> listAllSubscribe() {
        return subscribeRepository.findAll();
    }

    @Override
    public Subscribe insertSubscribe(Subscribe subscribe) {
        return subscribeRepository.save(subscribe);
    }

    @Override
    public void deleteSubscribeById(Integer subscribeId) {
        subscribeRepository.deleteSubscribeBySubscribeId(subscribeId);
    }

    @Override
    public Subscribe selectSubscribeById(Integer subscribeId) {
        return subscribeRepository.findSubscribeBySubscribeId(subscribeId);
    }

    @Override
    public void updateSubscribe(Subscribe subscribe) {
        subscribeRepository.save(subscribe);
    }
}
