package com.example.artplanref.repository;

import com.example.artplanref.model.entity.HaftalikPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaftalikPlanRepository extends JpaRepository<HaftalikPlanEntity, Long> {
}
