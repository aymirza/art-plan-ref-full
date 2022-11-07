package com.example.artplanref.model.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "haftalik_plan")
public class HaftalikPlanEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Date plan_date;
    public String sap_code;
    public String full_name_model;
    public String name_model;
    public String brand;
    public String color;
    public String shipment;
    public int quantity;
}
