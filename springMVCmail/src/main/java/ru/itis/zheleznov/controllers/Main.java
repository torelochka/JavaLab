package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.services.TestService;

@Controller
public class Main {


    @GetMapping("/")
    public String main() {
        return "index";
    }
}
