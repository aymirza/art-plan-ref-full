package com.example.artplanref.repository;

import com.example.artplanref.model.entity.KunlikPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KunlikPlanRepository extends JpaRepository<KunlikPlanEntity,Long> {
}
