package com.wust30405.ricp.task.repository;

import com.wust30405.ricp.task.entity.Xarm;
import com.wust30405.ricp.task.entity.Xarm;
import com.wust30405.ricp.task.entity.Xarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface XarmRepository extends JpaRepository<Xarm, Integer> {

    @Override
    List<Xarm> findAll();

    @Transactional
    @Modifying
    void deleteXarmByPostureId(Integer postureId);

    Xarm findXarmByPostureId(Integer postureId);

}
