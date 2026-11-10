package com.wust30405.ricp.plan.controller;

import com.wust30405.ricp.plan.entity.Plan;
import com.wust30405.ricp.plan.feign.TaskFeign;
import com.wust30405.ricp.plan.quartz.job.executeJob;
import com.wust30405.ricp.plan.service.IPlanService;
import com.wust30405.ricp.task.entity.Task;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("plan")
@RefreshScope
public class PlanController {
    
    private final IPlanService planService;

    private final TaskFeign taskFeign;

    private final Scheduler scheduler;

    public PlanController(IPlanService planService, TaskFeign taskFeign, @Qualifier("Scheduler") Scheduler scheduler) {
        this.planService = planService;
        this.taskFeign = taskFeign;
        this.scheduler = scheduler;
    }

    @GetMapping("/listPlanIndex")
    public String listPlanIndex(Model model){
        List<Plan> planList = planService.listAllPlan();
        model.addAttribute("plans", planList);
        return "planList";
    }

    @RequestMapping("/listAllPlan")
    @ResponseBody
    public List<Plan> listPlan(){
        List<Plan> planList = planService.listAllPlan();
        return planList;
    }

    /*新建计划的页面*/
    @RequestMapping("/insertPlanIndex")
    public String addPlanIndex(Model model) {
//        List<Task> tasks = taskFeign.listAllTask();
//        model.addAttribute("tasks",tasks);
        return "addPlan";
    }

    /*新建计划*/
    @RequestMapping("/insertPlan")
    public String addPlan(@RequestBody Plan plan) throws Exception {
        planService.insertPlan(plan);
//        String planId = String.valueOf(plan.getPlanId());
//        String cron = plan.getExecuteTime();
//        addJob(planId,"inspection",cron);
        return "planList";
    }


    /*添加调度任务*/
    public void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{
        // 启动调度器
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(executeJob.class).withIdentity(jobClassName, jobGroupName).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        /*.startAt(time)*/
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
    }

    /*更新调度时间*/
    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception
    {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }


    @GetMapping("/deletePlan")
    public String deleteTask(Integer planId){
        planService.deletePlanById(planId);
        return "planList";
    }


    @GetMapping("/editPlanIndex")
    public String editPlan(Integer planId, Model model) {
        Plan plans = planService.selectPlanById(planId);
        model.addAttribute("plans",plans);
        return "editPlan";
    }


    @RequestMapping("/updatePlan")
    public String updatePlan(@RequestBody Plan plan) {
        planService.updatePlan(plan);
        return "planList";
    }



}
