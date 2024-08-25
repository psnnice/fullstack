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
import com.workshop.student.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping({"","/"})
    public String getAll(ModelMap mode) {
        System.out.println("--------- getAll --------");

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        mode.addAttribute("faculties", faculties);
        return "faculty/index";
    }

    @GetMapping({"/edit/{faculty-id}","/{faculty-id}"})
    public String getById(
        ModelMap model,
        @PathVariable(name = "faculty-id") Integer facultyId
    ) {
        // System.out.println("--------- getById --------");
        // System.out.println("faculty-Id: " + facultyId);

        FacultyEntity entity = facultyService.getFacultyById(facultyId);
        // System.out.println("--------- getById() result--------");
        // System.out.println("faculty: " + entity.getFacultyName());
        model.addAttribute("faculty", entity);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        return "faculty/index";
    }

    @GetMapping("/delete/{faculty-id}")
    public String getdeleteById(
        ModelMap model,
        @PathVariable(name = "faculty-id") Integer facultyId
    ) {
        // System.out.println("--------- deleteById --------");
        // System.out.println("faculty-Id: " + facultyId);

        // System.out.println("--------- deleteById() result --------");
        facultyService.deleteFacultyById(facultyId);
        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);
        

        // facultyService.deleteFacultyById(facultyId);
        return "faculty/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param
    ){
        // System.out.println("--------- postInsertAndUpdate --------");
        // System.out.println("faculty-Id: " + param.get("faculty-Id"));
        // System.out.println("faculty-name: " + param.get("faculty-name"));

        // System.out.println("--------- postInsertAndUpdate() result --------");
        FacultyEntity entity = new FacultyEntity();
        if(null != param.get("faculty-id")){
            entity.setFacultyId(Integer.parseInt(param.get("faculty-id")));
        }
        entity.setFacultyName(param.get("faculty-name"));
        facultyService.saveFaculty(entity);
        // System.out.println("faculty-Id: " + result.getFacultyId());
        // System.out.println("faculty: " + result.getFacultyName());
        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        return "faculty/index";
    }


}
