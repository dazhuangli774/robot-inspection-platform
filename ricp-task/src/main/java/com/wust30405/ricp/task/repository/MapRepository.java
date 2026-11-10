package com.wust30405.ricp.task.repository;

import com.wust30405.ricp.task.entity.Map;
import com.wust30405.ricp.task.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Integer> {
    @Override
    List<Map> findAll();

    @Transactional
    @Modifying
    void deleteMapByMapId(Integer mapId);

    Map findMapByMapId(Integer mapId);
}
