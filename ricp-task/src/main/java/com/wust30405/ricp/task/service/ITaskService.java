package com.wust30405.ricp.task.service;


import com.wust30405.ricp.task.entity.Task;

import java.util.List;

public interface ITaskService {
    
    List<Task> listAllTask();

    Task insertTask(Task task);

    /*删除任务*/
    void deleteTaskById(Integer taskId);

    /*编辑任务*/
    Task selectTaskById(Integer taskId);

    /*更新*/
    void updateTask(Task task);


}
