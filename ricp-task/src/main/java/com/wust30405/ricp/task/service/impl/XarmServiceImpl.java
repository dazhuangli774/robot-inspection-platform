package com.wust30405.ricp.task.service.impl;

import com.wust30405.ricp.task.entity.Xarm;
import com.wust30405.ricp.task.feign.ArmControlFegin;
import com.wust30405.ricp.task.repository.XarmRepository;
import com.wust30405.ricp.task.service.IXarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XarmServiceImpl implements IXarmService {


    private final XarmRepository xarmRepository;

    public XarmServiceImpl(XarmRepository xarmRepository) {
        this.xarmRepository = xarmRepository;
    }

    @Override
    public List<Xarm> listAllXarm() {
        return xarmRepository.findAll();
    }

    @Override
    public Xarm insertXarm(Xarm xarm) {
        return xarmRepository.save(xarm);
    }

    @Override
    public void deleteXarmById(Integer postureId) {
        xarmRepository.deleteXarmByPostureId(postureId);
    }

    @Override
    public Xarm selectXarmById(Integer postureId) {
        return xarmRepository.findXarmByPostureId(postureId);
    }

    @Override
    public void updateXarm(Xarm xarm) {
        xarmRepository.save(xarm);
    }
}
