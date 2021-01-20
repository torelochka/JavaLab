package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @GetMapping("/advantages")
    public String advantagesPage() {
        return "advantages";
    }

    @GetMapping("/stages")
    public String stagesPage() {
        return "stages";
    }
}
