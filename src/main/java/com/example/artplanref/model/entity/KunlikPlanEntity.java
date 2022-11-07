package com.example.artplanref.model.entity;

import javax.persistence.*;
import java.sql.Date;



@Entity
@Table(name = "kunlik_plan")
public class KunlikPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Date plan_date;
    public Integer smena1;
    public Integer smena2;

    public KunlikPlanEntity() {
    }

    public KunlikPlanEntity(Long id, Date plan_date, Integer smena1, Integer smena2) {
        this.id = id;
        this.plan_date = plan_date;
        this.smena1 = smena1;
        this.smena2 = smena2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(Date plan_date) {
        this.plan_date = plan_date;
    }

    public Integer getSmena1() {
        return smena1;
    }

    public void setSmena1(Integer smena1) {
        this.smena1 = smena1;
    }

    public Integer getSmena2() {
        return smena2;
    }

    public void setSmena2(Integer smena2) {
        this.smena2 = smena2;
    }
}
