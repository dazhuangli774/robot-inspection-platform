package com.wust30405.ricp.user.controller;

import com.wust30405.ricp.plan.entity.Plan;
import com.wust30405.ricp.record.entity.Record;
import com.wust30405.ricp.task.entity.Task;
import com.wust30405.ricp.user.feign.DeviceFeign;
import com.wust30405.ricp.user.feign.PlanFeign;
import com.wust30405.ricp.user.feign.RecordFeign;
import com.wust30405.ricp.user.feign.TaskFeign;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("page")
public class PageController {


    private final TaskFeign taskFeign;
    private final DeviceFeign deviceFeign;
    private final PlanFeign planFeign;
    private final RecordFeign recordFeign;

    public PageController(TaskFeign taskFeign, DeviceFeign deviceFeign, PlanFeign planFeign, RecordFeign recordFeign) {
        this.taskFeign = taskFeign;
        this.deviceFeign = deviceFeign;
        this.planFeign = planFeign;
        this.recordFeign = recordFeign;
    }

    @RequestMapping("index")
    public String getIndex(){
        return "index";
    }


    @RequestMapping("homepage")
    public String getHomepage(Model model){
        return "homepage";
    }


//    @ResponseBody
    @RequestMapping("taskPage")
    public String getTaskPage(Model model) {
        List<Task> taskList = taskFeign.listAllTask();
//        System.out.println(taskList.toString());
        model.addAttribute("tasks", taskList);
        return "taskList";
    }

    @RequestMapping("planPage")
    public String getPlanPage(Model model) {
        List<Plan> planList = planFeign.listAllPlan();
        model.addAttribute("plans", planList);
        return "planList";
    }

    @RequestMapping("recordPage")
    public String getRecordPage(Model model) {
        List<Record> recordList = recordFeign.listAllRecord();
        model.addAttribute("records", recordList);
        return "historyData";
    }

    @RequestMapping("mapPage")
    public String getMapPage() {
        return "map";
    }

    @ResponseBody
    @RequestMapping("addTaskPage")
    public String getAddTaskPage() {
        return taskFeign.insertTaskIndex();
    }

    @ResponseBody
    @RequestMapping("addPlanPage")
    public String getAddPlanPage() {
        return planFeign.insertPlanIndex();
    }

    @ResponseBody
    @RequestMapping("insertTask")
    public String insertTask(Task task) {
        task.setCreateBy(getHttpSession());
        return taskFeign.insertTask(task);
    }

    @ResponseBody
    @RequestMapping("insertPlan")
    public String insertPlan(Plan plan) {
        plan.setCreateBy(getHttpSession());
        return planFeign.insertPlan(plan);
    }

    @ResponseBody
    @RequestMapping("editTask")
    public String getEditTaskPage(Integer taskId) {
        return taskFeign.editTaskIndex(taskId);
    }

    @ResponseBody
    @RequestMapping("editPlan")
    public String getEditPlanPage(Integer planId) {
        return planFeign.editPlanIndex(planId);
    }

    @ResponseBody
    @RequestMapping("updateTask")
    public String updateTask(Task task) {
        Date dateNow = new Date();
        task.setCreateTime(dateNow);
        task.setCreateBy(getHttpSession());
        return taskFeign.updateTask(task);
    }


    @ResponseBody
    @RequestMapping("updatePlan")
    public String updatePlan(Plan plan) {
        Date dateNow = new Date();
        plan.setCreateTime(dateNow);
        plan.setCreateBy(getHttpSession());
        return planFeign.updatePlan(plan);
    }

    @RequestMapping("deleteTask")
    public String deleteTask(Integer taskId) {
        taskFeign.deleteTask(taskId);
        return "redirect:/page/taskPage";
    }

    @RequestMapping("deletePlan")
    public String deletePlan(Integer planId) {
        planFeign.deletePlan(planId);
        return "redirect:/page/planPage";
    }

    @ResponseBody
    @RequestMapping("listAllDevice")
    public List listAllDevice() {
        return deviceFeign.listAllDevice();
    }

    @ResponseBody
    @RequestMapping("listAllTask")
    public List listAllTask() {
        return taskFeign.listAllTask();
    }

    @ResponseBody
    @RequestMapping("listAllRecord")
    public List listAllRecord() {
        return recordFeign.listAllRecord();
    }


    @ResponseBody
    @RequestMapping("getSession")
    public String getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("loginUser") + "  ID: " + session.getId());
        return (String) session.getAttribute("loginUser");
    }




}
