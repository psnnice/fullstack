package com.workshop.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop.student.entity.CourseEntity;
import com.workshop.student.entity.EnrollEntity;
import com.workshop.student.entity.StudentEntity;
import com.workshop.student.service.CourseService;
import com.workshop.student.service.EnrollService;
import com.workshop.student.service.StudentService;

@Controller
@RequestMapping("/enroll")
public class EnrollController {
    
    @Autowired
    private EnrollService enrollService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping({"","/"})
    public String getAll() {
        
        System.out.println("--------- getAll --------");

        List<EnrollEntity> enrolls = enrollService.getEnrollAll();
        System.out.println("--------- EnrollController getAll() Result --------");
        System.out.println("enrolls: " + enrolls.size()); // จำนวนข้อมูลที่ได้จากการ query size จากฐานข้อมูล
        return "enroll/index";
    }

    @GetMapping("/{enroll-Id}")
    public String getById(
        @PathVariable(name = "enroll-Id") Integer enrollId
    ) {
        System.out.println("--------- getById --------");
        System.out.println("enroll-Id: " + enrollId);

        EnrollEntity entity = enrollService.getEnrollById(enrollId);
        System.out.println("--------- EnrollController getById() result--------");
        System.out.println("enroll: " + entity.getCourse().getCourseName());
        System.out.println("enroll: " + entity.getStudent().getStudentFirstName());
        System.out.println("enroll: " + entity.getStudent().getStudentLastName());
        return "enroll/index";
    }

    @GetMapping("/delete/{enroll-Id}")
    public String getdeleteById(
        @PathVariable(name = "enroll-Id") Integer enrollId
    ) {
        System.out.println("--------- deleteById --------");
        System.out.println("enroll-Id: " + enrollId);

        System.out.println("--------- EnrollController deleteById() result --------");
        enrollService.deleteEnrollById(enrollId);
        return "enroll/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        @RequestParam() Map<String, String> param
    ){
        System.out.println("--------- postInsertAndUpdate --------");
        System.out.println("enroll-Id: " + param.get("enroll-id"));
        System.out.println("enroll-name: " + param.get("enroll-name"));
        
        System.out.println("--------EnrollController postInsertAndUpdate() Result---------");
        Integer courseId = Integer.parseInt(param.get("course-id"));
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        System.out.println("course: " + courseEntity.getCourseId());

        Integer studentId = Integer.parseInt(param.get("student-id"));
        StudentEntity studentEntity = studentService.getStudentById(studentId);
        System.out.println("student: " + studentEntity.getStudentId());

        EnrollEntity entity = new EnrollEntity();
        if(null != param.get("enroll-id")){
            entity.setEnrollId(Integer.parseInt(param.get("enroll-id")));
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        System.out.println("enroll-Id: " + result.getEnrollId());
        System.out.println("courseName: " + result.getCourse().getCourseName());
        System.out.println("student firstname: " + result.getStudent().getStudentFirstName());
        return "enroll/index";
    }


}

