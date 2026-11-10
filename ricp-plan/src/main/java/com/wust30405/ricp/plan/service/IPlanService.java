package com.wust30405.ricp.plan.service;


import com.wust30405.ricp.plan.entity.Plan;

import java.util.List;


public interface IPlanService {
    List<Plan> listAllPlan();

    Plan insertPlan(Plan plan);

    void deletePlanById(Integer planId);

    Plan selectPlanById(Integer planId);

    void updatePlan(Plan plan);
}
