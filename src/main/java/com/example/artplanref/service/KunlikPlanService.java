package com.example.artplanref.service;

import com.example.artplanref.model.KunlikPlan;
import com.example.artplanref.model.entity.KunlikPlanEntity;

import java.util.List;

public interface KunlikPlanService {
    List<KunlikPlanEntity> getAllKunlikPlan();
    KunlikPlanEntity insert(KunlikPlanEntity kunlikPlanEntity);

}
