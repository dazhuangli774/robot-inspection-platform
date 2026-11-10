package com.wust30405.ricp.record.controller;

import com.wust30405.ricp.common.util.AjaxResult;
import com.wust30405.ricp.record.entity.Record;
import com.wust30405.ricp.record.feign.UserFeign;
import com.wust30405.ricp.record.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("record")
@RefreshScope
public class RecordController {
    
    @Autowired
    private UserFeign userFeign;
    
    @Autowired
    private IRecordService recordService;
    
    
    @ResponseBody
    @GetMapping("checkUserType")
    public AjaxResult checkUserType(Integer userId) {
        String userType = userFeign.checkUserType(userId);
        if (userType.equals("01")) {
            return AjaxResult.success("success", true);
        }
        return AjaxResult.success("success", false);
    }


    @ResponseBody
    @RequestMapping(value = "/updateImg", method = RequestMethod.GET)
    String updateImg(@RequestParam("imgPath") String imgPath) throws IOException {
        return recordService.uploadImg(imgPath);
    }
    
    @ResponseBody
    @RequestMapping(value = "/batchInsertRecord", method = RequestMethod.GET)
    void batchInsertRecord(@RequestParam("recordList") List<Record> recordList){
        for (Record record : recordList) {
            recordService.insertRecord(record);
        }
    }

    @GetMapping("/deleteRecord")
    public String deleteRecord(Integer recordId){
        recordService.deleteRecordById(recordId);
        return "redirect:listAll";
    }


    @GetMapping("/editRecord")
    public String editRecord(Integer recordId, Model model) {
        Record records = recordService.selectRecordById(recordId);
        model.addAttribute("records",records);
        return "editRecord";
    }


    @RequestMapping("/updateRecord")
    public String updateRecord(Record sysInspectionRecord) {
        recordService.updateRecord(sysInspectionRecord);
        return "planlist";
    }



//    @RequestMapping("/listAll")
//    public String listAll(Model model) {
//        List<Record> records = recordService.listAllRecord();
//        model.addAttribute("records",records);
//        return "planlist";
//    }

    @RequestMapping("/listAllRecord")
    @ResponseBody
    public List listAllRecord(){
        return recordService.listAllRecord();
    }

    @RequestMapping("/getRecordsByRecordId")
    @ResponseBody
    public List getRecordsByRecordId(List<Integer> recordIdList) {
        List<Record> recordList = new ArrayList<>();
        for (Integer recordId : recordIdList) {
            recordList.add(recordService.selectRecordById(recordId));
        }
        return recordList;
    }

    @RequestMapping("/getRecordByRecordId")
    @ResponseBody
    public Record getRecordByRecordId(Integer recordId) {
        return recordService.selectRecordById(recordId);
    }

    @RequestMapping("insertRecord")
    public AjaxResult insertRecord(Record record) {
        recordService.insertRecord(record);
        return AjaxResult.success();
    }
}
