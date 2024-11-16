package com.capstone.capstoneProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "loginTest";
//    }
//
    @GetMapping("/successPage")
    public String loginSuccess() {
        return "successPage";
    }
}

