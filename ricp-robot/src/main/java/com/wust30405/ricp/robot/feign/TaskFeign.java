package com.wust30405.ricp.robot.feign;

import com.wust30405.ricp.task.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "http://localhost:8082/task", name = "task")
public interface TaskFeign {
    @RequestMapping(value = "/getTaskByTaskId", method = RequestMethod.GET)
    Task getTask(@RequestParam("taskId") Integer taskId);
}
