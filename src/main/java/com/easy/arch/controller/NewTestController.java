package com.easy.arch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newtest")
public class NewTestController {
    @RequestMapping("login")
    public String login(){
        return "newlogin";
    }
}
