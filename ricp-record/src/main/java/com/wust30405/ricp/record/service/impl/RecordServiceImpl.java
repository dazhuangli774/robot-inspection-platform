package com.wust30405.ricp.record.service.impl;

import com.wust30405.ricp.oss.utils.OSSUtil;
import com.wust30405.ricp.record.entity.Record;
import com.wust30405.ricp.record.repository.RecordRepository;
import com.wust30405.ricp.record.service.IRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RecordServiceImpl implements IRecordService {

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> listAllRecord() {
        return recordRepository.findAll();
    }

    @Override
    public Record insertRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public void deleteRecordById(Integer recordId) {
        recordRepository.deleteRecordByRecordId(recordId);
    }

    @Override
    public Record selectRecordById(Integer recordId) {
        return recordRepository.findRecordByRecordId(recordId);
    }

    @Override
    public void updateRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public String uploadImg(String img) throws IOException {

        File imgFile = new File(img);
        FileInputStream fileInputStream = new FileInputStream(imgFile);
        MultipartFile multipartFile = new MockMultipartFile(imgFile.getName(), imgFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        OSSUtil ossUtil = new OSSUtil();
        return ossUtil.uploadImg2Oss(multipartFile, fileName);

    }
}
