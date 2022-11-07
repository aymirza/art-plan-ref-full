package com.example.artplanref.service;

import com.example.artplanref.model.HaftalikPlan;
import com.example.artplanref.model.entity.HaftalikPlanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HaftalikPlanService {
    List<HaftalikPlanEntity> getAllHaftalikPlan();

}
