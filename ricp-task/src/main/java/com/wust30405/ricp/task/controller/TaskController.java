package com.wust30405.ricp.task.controller;

import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.task.entity.Task;
import com.wust30405.ricp.task.feign.UserFeign;
import com.wust30405.ricp.task.service.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("task")
@CrossOrigin
public class TaskController {

    private final ITaskService taskService;

    private final UserFeign userFeign;

    public TaskController(ITaskService taskService, UserFeign userFeign) {
        this.taskService = taskService;
        this.userFeign = userFeign;
    }

    @ResponseBody
    @GetMapping("checkUserType")
    public AjaxResult checkUserType(Integer userId) {
        String userType = userFeign.checkUserType(userId);
        if (userType.equals("01")) {
            return AjaxResult.success("success", true);
        }
        return AjaxResult.success("success", false);
    }

    @RequestMapping("/insertTaskIndex")
    public String insertTaskIndex() {
        return "addTask";
    }


    @RequestMapping("/insertTask")
    public String insertTask(@RequestBody Task task) {
        taskService.insertTask(task);
        return "taskList";
    }


    @GetMapping("/deleteTask")
    public String deleteTask(Integer taskId){
        taskService.deleteTaskById(taskId);
        return "taskList";
    }


    @GetMapping("/editTaskIndex")
    public String editTask(Integer taskId, Model model) {
        Task tasks = taskService.selectTaskById(taskId);
        model.addAttribute("tasks",tasks);
        return "editTask";
    }


    @RequestMapping("/updateTask")
    public String updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return "taskList";
    }



    @RequestMapping("/listAllTaskIndex")
    public String listAllTaskIndex(@RequestBody Model model) {
        List<Task> tasks = taskService.listAllTask();
        model.addAttribute("tasks",tasks);
        return "taskList";
    }

    @RequestMapping("/listAllTask")
    @ResponseBody
    public List listAllTask(){
        return taskService.listAllTask();
    }

    @RequestMapping("/getTasksByTaskId")
    @ResponseBody
    public List getTasksByTaskId(List<Integer> taskIdList) {
        List<Task> taskList = new ArrayList<>();
        for (Integer taskId : taskIdList) {
            taskList.add(taskService.selectTaskById(taskId));
        }
        return taskList;
    }

    @RequestMapping("/getTaskByTaskId")
    @ResponseBody
    public Task getTaskByTaskId(Integer taskId) {
        return taskService.selectTaskById(taskId);
    }
}
