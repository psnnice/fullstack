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
import com.workshop.student.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping({"","/"})
    public String getAll() {
        System.out.println("--------- getAll --------");
        
        List<CourseEntity> courses = courseService.getCourseAll();
        System.out.println("--------- getAll() Result --------");
        System.out.println("courses: " + courses.size());

        return "course/index";
    }

    @GetMapping("/{course-id}")
    public String getById(
        @PathVariable(name = "course-id") Integer courseId
    ) {
        System.out.println("--------- getById --------");
        System.out.println("course-id: " + courseId);

        CourseEntity entity = courseService.getCourseById(courseId);
        System.out.println("--------- Coursecontroll getById() result--------");
        System.out.println("course name : " + entity.getCourseName());
        return "course/index";
    }

    @GetMapping("/delete/{course-id}")
    public String getdeleteById(
        @PathVariable(name = "course-id") Integer courseId
    ) {
        System.out.println("--------- deleteById --------");
        System.out.println("course-id: " + courseId);

        System.out.println("--------- CourseControll deleteById() result --------");
        courseService.deleteCourseById(courseId);

        return "course/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        @RequestParam() Map<String, String> param 
        // map คือโครงสร้างข้อมูลที่เก็บข้อมูลในรูปแบบ key-value โดย key คือชื่อของ input และ value คือค่าที่กรอกใน input นั้นๆ
        // ดังนั้นค่าที่ส่งมาจะถูกเก็บในตัวแปร param ซึ่งเป็น Map ที่มี key คือชื่อของ input และ value คือค่าที่กรอกใน input
        // ในที่นี้ key คือ course-id และ course-name ซึ่งเป็นชื่อของ input ที่ส่งมาจาก form
        // และ value คือค่าที่กรอกใน input นั้นๆ
    ){
        System.out.println("--------- postInsertAndUpdate --------");
        System.out.println("course-id: " + param.get("course-id"));
        System.out.println("course-name: " + param.get("course-name"));

        System.out.println("--------- postInsertAndUpdate() result --------");
        CourseEntity entity = new CourseEntity();
        if(null != param.get("course-id")){
            entity.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        entity.setCourseName(param.get("course-name"));
        entity.setCourseDescription(param.get("course-des"));
        CourseEntity result = courseService.saveCourse(entity);
        System.out.println("course-id: " + result.getCourseId());
        System.out.println("course: " + result.getCourseName());
        return "course/index";
    }


}

