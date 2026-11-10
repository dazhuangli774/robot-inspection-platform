package com.wust30405.ricp.task.repository;

import com.wust30405.ricp.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Override
    List<Task> findAll();

    @Transactional
    @Modifying
    void deleteTaskByTaskId(Integer taskId);

    Task findTaskByTaskId(Integer taskId);

}
