package com.wust30405.ricp.user.service;


import com.wust30405.ricp.user.entity.Subscribe;

import java.util.List;

public interface ISubscribeService {

    List<Subscribe> listAllSubscribe();

    Subscribe insertSubscribe(Subscribe subscribe);


    void deleteSubscribeById(Integer subscribeId);


    Subscribe selectSubscribeById(Integer subscribeId);


    void updateSubscribe(Subscribe subscribe);


}
