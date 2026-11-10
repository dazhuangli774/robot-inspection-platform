package com.wust30405.ricp.task.service.impl;

import com.wust30405.ricp.task.entity.Task;
import com.wust30405.ricp.task.feign.UserFeign;
import com.wust30405.ricp.task.repository.TaskRepository;
import com.wust30405.ricp.task.service.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    private final UserFeign userFeign;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserFeign userFeign, TaskRepository taskRepository) {
        this.userFeign = userFeign;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task insertTask(Task task) {
        task.setStatus("0");
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Integer taskId) {
        taskRepository.deleteTaskByTaskId(taskId);
    }

    @Override
    public Task selectTaskById(Integer taskId) {
        return taskRepository.findTaskByTaskId(taskId);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
