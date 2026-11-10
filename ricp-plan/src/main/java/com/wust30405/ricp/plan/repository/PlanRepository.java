package com.wust30405.ricp.plan.repository;

import com.wust30405.ricp.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Override
    List<Plan> findAll();

    @Transactional
    @Modifying
    void deletePlanByPlanId(Integer planId);

    Plan findPlanByPlanId(Integer planId);
}
