package com.example.artplanref.service.serviceImpl;

import com.example.artplanref.model.KunlikPlan;
import com.example.artplanref.model.entity.KunlikPlanEntity;
import com.example.artplanref.repository.KunlikPlanRepository;
import com.example.artplanref.service.KunlikPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KunlikPlanServiceImpl implements KunlikPlanService {

    @Autowired
    KunlikPlanRepository kunlikPlanRepository;

    @Override
    public List<KunlikPlanEntity> getAllKunlikPlan() {
        return kunlikPlanRepository.findAll();
    }

    @Override
    public KunlikPlanEntity insert(KunlikPlanEntity kunlikPlanEntity) {
        return kunlikPlanRepository.save(kunlikPlanEntity);
    }
}
