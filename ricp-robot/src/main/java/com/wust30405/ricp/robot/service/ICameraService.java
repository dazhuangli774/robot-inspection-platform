package com.wust30405.ricp.robot.service;

import ClientDemo.ClientDemo;


public interface ICameraService {
    ClientDemo initCamera();
    boolean SingleCapture(ClientDemo demo, String imgPath);
}
