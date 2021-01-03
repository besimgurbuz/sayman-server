package com.sayman.server.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/calender")
    public String getCalender() {
        return "calender";
    }
}
