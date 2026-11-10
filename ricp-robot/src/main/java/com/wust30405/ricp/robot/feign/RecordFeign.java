package com.wust30405.ricp.robot.feign;

import com.wust30405.ricp.record.entity.Record;
import com.wust30405.ricp.task.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8086/record", name = "record")
public interface RecordFeign {

    @RequestMapping(value = "/updateImg", method = RequestMethod.GET)
    String updateImg(@RequestParam("imgPath") String imgPath);

    @RequestMapping(value = "/batchInsertRecord", method = RequestMethod.GET)
    void batchInsertRecord(@RequestParam("recordList") List<Record> recordList);

}
