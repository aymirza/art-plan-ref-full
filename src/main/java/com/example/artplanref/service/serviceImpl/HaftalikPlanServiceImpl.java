package com.example.artplanref.service.serviceImpl;

import com.example.artplanref.model.entity.HaftalikPlanEntity;
import com.example.artplanref.repository.HaftalikPlanRepository;
import com.example.artplanref.service.HaftalikPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaftalikPlanServiceImpl implements HaftalikPlanService {

    @Autowired
    HaftalikPlanRepository haftalikPlanRepository;

    @Override
    public List<HaftalikPlanEntity> getAllHaftalikPlan() {
        return haftalikPlanRepository.findAll();
    }
}
