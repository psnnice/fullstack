package com.workshop.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomePageController {
    @GetMapping("/")
    public String GetTutorial(){
        return "template";
    }
}
