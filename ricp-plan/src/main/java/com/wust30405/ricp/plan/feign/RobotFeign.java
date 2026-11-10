package com.wust30405.ricp.plan.feign;

import com.wust30405.ricp.task.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8084/robot", name = "robot")
public interface RobotFeign {
    @RequestMapping(value = "/performInspectionTasks", method = RequestMethod.GET)
    void performInspectionTasks(@RequestParam("taskList") List<Integer> taskIdList);

    @RequestMapping(value = "/getRobotStatus", method = RequestMethod.GET)
    String getRobotStatus();
}
