package com.wust30405.ricp.record.repository;

import com.wust30405.ricp.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Override
    List<Record> findAll();

    @Transactional
    @Modifying
    void deleteRecordByRecordId(Integer recordId);

    Record findRecordByRecordId(Integer recordId);
}
