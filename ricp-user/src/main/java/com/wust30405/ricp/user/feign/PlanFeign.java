package com.wust30405.ricp.user.feign;

import com.wust30405.ricp.plan.entity.Plan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(url = "http://localhost:8083/plan", name = "plan")
public interface PlanFeign {

    @RequestMapping(value = "/listAllPlanIndex", method = RequestMethod.GET)
    String listAllPlanIndex();

    @RequestMapping(value = "/listAllPlan", method = RequestMethod.GET)
    List listAllPlan();

    @RequestMapping(value = "/insertPlanIndex", method = RequestMethod.GET)
    String insertPlanIndex();

    @RequestMapping(value = "/insertPlan", method = RequestMethod.POST)
    String insertPlan(@RequestBody Plan plan);

    @RequestMapping(value = "/editPlanIndex", method = RequestMethod.GET)
    String editPlanIndex(@RequestParam("planId")Integer planId);

    @RequestMapping(value = "/updatePlan", method = RequestMethod.GET)
    String updatePlan(@RequestBody Plan plan);

    @RequestMapping(value = "/deletePlan", method = RequestMethod.GET)
    String deletePlan(@RequestParam("planId") Integer planId);
}
