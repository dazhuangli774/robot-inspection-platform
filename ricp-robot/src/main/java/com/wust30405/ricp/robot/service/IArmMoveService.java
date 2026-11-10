package com.wust30405.ricp.robot.service;


public interface IArmMoveService {

    void executeInstructionById(Integer instructionId);
    void executeInstruction(String armData);
    boolean getXarmStatus();
    void backToZeroPoint();
    String getInstruction();
}
