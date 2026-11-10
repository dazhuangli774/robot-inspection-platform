package com.wust30405.ricp.robot.service.impl;

import ClientDemo.ClientDemo;
import ClientDemo.HCNetSDK;
import com.wust30405.ricp.robot.service.ICameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl implements ICameraService {

    private static Logger _log = LoggerFactory.getLogger(CameraServiceImpl.class);

    @Override
    public ClientDemo initCamera() {


        boolean initSuc = HCNetSDK.INSTANCE.NET_DVR_Init();
        if(!initSuc){
            _log.error("初始化sdk失败，错误代码：" + String.valueOf(HCNetSDK.INSTANCE.NET_DVR_GetLastError()));
            return null;
        }
        ClientDemo demo = new ClientDemo();
        demo.CameraInit("192.168.10.100","8000","admin","SZWKHH");
        if (HCNetSDK.INSTANCE.NET_DVR_GetLastError() != 0){
            _log.error("初始化相机失败，错误代码：" + String.valueOf(HCNetSDK.INSTANCE.NET_DVR_GetLastError()));
            return null;

        }
        return demo;
    }

    @Override
    public boolean SingleCapture(ClientDemo demo, String imgPath) {
        return demo.SingleCapture(imgPath);
    }
}
