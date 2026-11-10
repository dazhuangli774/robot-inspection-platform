package com.wust30405.ricp.record.service;


import com.wust30405.ricp.record.entity.Record;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IRecordService {

    List<Record> listAllRecord();

    Record insertRecord(Record record);

    void deleteRecordById(Integer recordId);

    Record selectRecordById(Integer recordId);

    void updateRecord(Record record);

    String uploadImg(String img) throws IOException;
}
