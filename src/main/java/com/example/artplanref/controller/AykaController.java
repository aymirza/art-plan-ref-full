package com.example.artplanref.controller;

import com.example.artplanref.model.OylikPlan;
import com.example.artplanref.model.entity.OylikPlanEntity;
import com.example.artplanref.repository.HaftalikPlanRepository;
import com.example.artplanref.repository.OylikPlanRepository;
import com.example.artplanref.service.HaftalikPlanService;
import com.example.artplanref.service.OylikPlanService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AykaController {




    @GetMapping("/hello")
    public String index() {
        return "hello";
    }









}
