package com.wust30405.ricp.plan.feign;

import com.wust30405.ricp.task.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8082/task", name = "task")
public interface TaskFeign {

    @RequestMapping(value = "/listAllTask", method = RequestMethod.GET)
    List<Task> listAllTask();

    @RequestMapping(value = "/getTasksByTaskId", method = RequestMethod.GET)
    String getTasksByTaskId(@RequestParam("taskIdList") List<Integer> taskIdList);
}
