package com.wust30405.ricp.user.feign;

import com.wust30405.ricp.record.entity.Record;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8086/record", name = "record")
public interface RecordFeign {

    @RequestMapping(value = "/listAllRecordIndex", method = RequestMethod.GET)
    String listAllRecordIndex();

    @RequestMapping(value = "/listAllRecord", method = RequestMethod.GET)
    List listAllRecord();

    @RequestMapping(value = "/insertRecordIndex", method = RequestMethod.GET)
    String insertRecordIndex();

    @RequestMapping(value = "/insertRecord", method = RequestMethod.POST)
    String insertRecord(@RequestBody Record record);

    @RequestMapping(value = "/editRecordIndex", method = RequestMethod.GET)
    String editRecordIndex(@RequestParam("recordId")Integer recordId);

    @RequestMapping(value = "/updateRecord", method = RequestMethod.GET)
    String updateRecord(@RequestBody Record record);

    @RequestMapping(value = "/deleteRecord", method = RequestMethod.GET)
    String deleteRecord(@RequestParam("recordId") Integer recordId);
}