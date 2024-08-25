package com.workshop.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.student.entity.FacultyEntity;
import com.workshop.student.entity.StudentEntity;
import com.workshop.student.service.FacultyService;
import com.workshop.student.service.StudentService;



@Controller
@RequestMapping("/student")
public class studentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping({"","/"})
    public String getAll(
        ModelMap models
    ) {
        // System.out.println("--------- getAll --------");
        

        List<StudentEntity> students = studentService.getStudentAll();
        models.addAttribute("students", students); //student คือชื่อที่ใช้ใน html ในการเรียกใช้ข้อมูล

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        models.addAttribute("faculties", faculties);
        // System.out.println("--------- studentControll getAll() Result --------");
        // System.out.println("students: " + students.size()); // จำนวนข้อมูลที่ได้จากการ query size จากฐานข้อมูล

        return "student/index";
    }

    @GetMapping("/{student-Id}")
    public String getById(
        ModelMap model,
        @PathVariable(name = "student-Id") Integer studentId
    ) {
        // System.out.println("--------- getById --------");
        // System.out.println("student-Id: " + studentId);

        StudentEntity entity = studentService.getStudentById(studentId);
        model.addAttribute("student", entity);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);
        // System.out.println("--------- getById() result--------");
        // System.out.println("student First name : " + entity.getStudentFirstName());
        // System.out.println("student Last name : " + entity.getStudentLastName());
        return "student/index";
    }

    @GetMapping("/delete/{student-Id}")
    public String getdeleteById(
        ModelMap model,
        @PathVariable(name = "student-Id") Integer studentId
    ) {
        // System.out.println("--------- deleteById --------");
        // System.out.println("student-Id: " + studentId);

        // System.out.println("--------- deleteById() result --------");
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);
        studentService.deleteStudentById(studentId);
        return "student/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param
    ){
        // System.out.println("--------- postInsertAndUpdate --------");
        // System.out.println("student-code: " + param.get("student-code"));
        // System.out.println("student-Id: " + param.get("student-Id"));
        // System.out.println("student-name: " + param.get("student-name"));

        // System.out.println("faculty-Id: " + param.get("faculty-Id"));

        // System.out.println("--------- studentcontroll postInsertAndUpdate() result --------");
        Integer facultyId = Integer.parseInt(param.get("faculty-Id"));

        FacultyEntity facultyEntity = facultyService.getFacultyById(facultyId);
        System.out.println(facultyEntity.getFacultyName());

        StudentEntity entity = new StudentEntity();
        if(null != param.get("student-id")){
            entity.setStudentId(Integer.parseInt(param.get("student-Id")));
        }
        entity.setStudentCode(param.get("student-code"));
        entity.setStudentFirstName(param.get("student-fname"));
        entity.setStudentLastName(param.get("student-lname"));
        entity.setFaculty(facultyEntity);
        studentService.saveStudent(entity);
        // System.out.println("student-Id: " + result.getStudentId());
        // System.out.println("student-code: " + result.getStudentFirstName());
        // System.out.println("student-name: " + result.getStudentLastName());
        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);
        return "student/index";
    }
}
