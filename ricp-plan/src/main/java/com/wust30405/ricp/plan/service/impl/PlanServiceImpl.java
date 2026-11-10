package com.wust30405.ricp.plan.service.impl;

import com.wust30405.ricp.plan.entity.Plan;
import com.wust30405.ricp.plan.repository.PlanRepository;
import com.wust30405.ricp.plan.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements IPlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public List<Plan> listAllPlan() {
        return planRepository.findAll();
    }

    @Override
    public Plan insertPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void deletePlanById(Integer planId) {
        planRepository.deletePlanByPlanId(planId);
    }

    @Override
    public Plan selectPlanById(Integer planId) {
        return planRepository.findPlanByPlanId(planId);
    }

    @Override
    public void updatePlan(Plan plan) {
        planRepository.save(plan);
    }
}
