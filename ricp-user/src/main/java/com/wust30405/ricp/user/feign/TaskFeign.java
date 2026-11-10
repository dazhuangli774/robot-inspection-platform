package com.wust30405.ricp.user.feign;

import com.wust30405.ricp.task.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8082/task", name = "task")
public interface TaskFeign {

    @RequestMapping(value = "/listAllTaskIndex", method = RequestMethod.GET)
    String listAllTaskIndex();

    @RequestMapping(value = "/listAllTask", method = RequestMethod.GET)
    List listAllTask();

    @RequestMapping(value = "/insertTaskIndex", method = RequestMethod.GET)
    String insertTaskIndex();

    @RequestMapping(value = "/insertTask", method = RequestMethod.POST)
    String insertTask(@RequestBody Task task);

    @RequestMapping(value = "/editTaskIndex", method = RequestMethod.GET)
    String editTaskIndex(@RequestParam("taskId")Integer taskId);

    @RequestMapping(value = "/updateTask", method = RequestMethod.GET)
    String updateTask(@RequestBody Task task);

    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    String deleteTask(@RequestParam("taskId") Integer taskId);
}
