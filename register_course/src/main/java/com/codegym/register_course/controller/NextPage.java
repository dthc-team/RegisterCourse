package com.codegym.register_course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("nextPage")
public class NextPage {
    @GetMapping("")
    public String showIndex(){
        return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
}
