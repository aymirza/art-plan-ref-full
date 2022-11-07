package com.example.artplanref.controller;

import com.example.artplanref.model.HaftalikPlan;
import com.example.artplanref.model.KunlikPlan;
import com.example.artplanref.model.OylikPlan;
import com.example.artplanref.model.Student;
import com.example.artplanref.model.entity.HaftalikPlanEntity;
import com.example.artplanref.model.entity.KunlikPlanEntity;
import com.example.artplanref.model.entity.OylikPlanEntity;
import com.example.artplanref.model.entity.StudentEntity;
import com.example.artplanref.repository.HaftalikPlanRepository;
import com.example.artplanref.repository.KunlikPlanRepository;
import com.example.artplanref.repository.OylikPlanRepository;
import com.example.artplanref.repository.StudentRepository;
import com.example.artplanref.service.HaftalikPlanService;
import com.example.artplanref.service.KunlikPlanService;
import com.example.artplanref.service.OylikPlanService;
import com.example.artplanref.service.StudentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {



    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private OylikPlanService oylikPlanService;

    @Autowired
    private OylikPlanRepository oylikPlanRepository;

    @Autowired
    private HaftalikPlanRepository haftalikPlanRepository;

    @Autowired
    private HaftalikPlanService haftalikPlanService;

    @Autowired
    private KunlikPlanService kunlikPlanService;

    @Autowired
    private KunlikPlanRepository kunlikPlanRepository;




    @GetMapping("/get-students")
    public List<StudentEntity> getAllSudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/get-oylikplan")
    public List<OylikPlanEntity> getAllOylikPlans(){
        return oylikPlanService.getAllOylikPlan();
    }

    @GetMapping("/get-haftalikplan")
    public List<HaftalikPlanEntity> getAllHaftalikPlans(){
        return haftalikPlanService.getAllHaftalikPlan();
    }

    @GetMapping("/get-kunlikplan")
    public List<KunlikPlanEntity> getAllKunlikPlans(){
        return kunlikPlanService.getAllKunlikPlan();
    }


    @PostMapping("/import-order-excel")
    public List<Student> importExcelFile(@RequestParam("file") MultipartFile files)throws IOException {
        List<Student> students = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        // Read student data form excel file sheet1.
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                Student student = new Student();
                student.studentNo = getCellValue1(row, 0);
                student.firstName = getCellValue1(row, 1);
                student.lastName = getCellValue1(row, 2);
                student.age = convertStringToInt(getCellValue1(row, 3));
                student.address = getCellValue1(row, 4);
                students.add(student);
            }
        }
        // Save to db.
        List<StudentEntity> entities = new ArrayList<>();
        if (students.size() > 0) {
            students.forEach(x->{
                StudentEntity entity = new StudentEntity();
                entity.student_no = x.studentNo;
                entity.first_name = x.firstName;
                entity.last_name = x.lastName;
                entity.age = x.age;
                entity.address = x.address;
                entities.add(entity);
            });
            studentRepository.saveAll(entities);
        }
        return students;
    }
    private int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }
    private String getCellValue1(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @PostMapping("/import-oylik-plan")
    public List<OylikPlan> importOylikPlan(@RequestParam("file") MultipartFile files) throws IOException {
        List<OylikPlan> oylikPlans = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook((files.getInputStream()));
        // Read oylik plan data form excel sheet1;
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                OylikPlan oylikPlan = new OylikPlan();
                oylikPlan.planDate = Date.valueOf(getCellValue2(row, 0));
                oylikPlan.sapCode = getCellValue2(row, 1);
                oylikPlan.fullNameModel = getCellValue2(row, 2);
                oylikPlan.nameModel = getCellValue2(row, 3);
                oylikPlan.brand = getCellValue2(row, 4);
                oylikPlan.color = getCellValue2(row, 5);
                oylikPlan.shipment = getCellValue2(row, 6);
                oylikPlan.quantity = Integer.valueOf(getCellValue2(row, 7));
                oylikPlans.add(oylikPlan);
            }
        }
        // Save to db
        List<OylikPlanEntity> oylikPlanEntities = new ArrayList<>();
        if (oylikPlans.size() > 0){
            oylikPlans.forEach(x->{
                OylikPlanEntity oylikPlanEntity = new OylikPlanEntity();
                oylikPlanEntity.plan_date = x.planDate;
                oylikPlanEntity.sap_code = x.sapCode;
                oylikPlanEntity.full_name_model = x.fullNameModel;
                oylikPlanEntity.name_model = x.nameModel;
                oylikPlanEntity.brand = x.brand;
                oylikPlanEntity.color = x.color;
                oylikPlanEntity.shipment = x.shipment;
                oylikPlanEntity.quantity = x.quantity;
                oylikPlanEntities.add(oylikPlanEntity);
            });
            oylikPlanRepository.saveAll(oylikPlanEntities);
        }
        return oylikPlans;
    }
    private String getCellValue2(Row row, int cellNo){
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @PostMapping("/import-haftalik-plan")
    public List<HaftalikPlan> importHaftalikPlan(@RequestParam("file") MultipartFile files) throws IOException {
        List<HaftalikPlan> haftalikPlans = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook((files.getInputStream()));
        // Read oylik plan data form excel sheet1;
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                HaftalikPlan haftalikPlan = new HaftalikPlan();
                haftalikPlan.planDate = Date.valueOf(getCellValue3(row, 0));
                haftalikPlan.sapCode = getCellValue3(row, 1);
                haftalikPlan.fullNameModel = getCellValue3(row, 2);
                haftalikPlan.nameModel = getCellValue3(row, 3);
                haftalikPlan.brand = getCellValue3(row, 4);
                haftalikPlan.color = getCellValue3(row, 5);
                haftalikPlan.shipment = getCellValue3(row, 6);
                haftalikPlan.quantity = Integer.valueOf(getCellValue3(row, 7));
                haftalikPlans.add(haftalikPlan );
            }
        }
        // Save to db
        List<HaftalikPlanEntity> haftalikPlanEntities = new ArrayList<>();
        if (haftalikPlans.size() > 0){
            haftalikPlans.forEach(x->{
                HaftalikPlanEntity haftalikPlanEntity = new HaftalikPlanEntity();
                haftalikPlanEntity.plan_date = x.planDate;
                haftalikPlanEntity.sap_code = x.sapCode;
                haftalikPlanEntity.full_name_model = x.fullNameModel;
                haftalikPlanEntity.name_model = x.nameModel;
                haftalikPlanEntity.brand = x.brand;
                haftalikPlanEntity.color = x.color;
                haftalikPlanEntity.shipment = x.shipment;
                haftalikPlanEntity.quantity = x.quantity;
                haftalikPlanEntities.add(haftalikPlanEntity);
            });
            haftalikPlanRepository.saveAll(haftalikPlanEntities);
        }
        return haftalikPlans;
    }
    private String getCellValue3(Row row, int cellNo){
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @PostMapping("/create-plan-day")
    public ResponseEntity<KunlikPlanEntity> saveKunlikPlan(@RequestBody KunlikPlanEntity kunlikPlanEntity){
        KunlikPlanEntity kunlikPlanEntity1 = kunlikPlanService.insert(kunlikPlanEntity);
      //  HttpHeaders httpHeaders = new HttpHeaders();
       // httpHeaders.add("kunlikPlan", "/api/kunlik-plan"+ kunlikPlanEntity1.getId().toString());
        return  new ResponseEntity<>(kunlikPlanEntity1, HttpStatus.CREATED);


    }




}