package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.zheleznov.models.SignUp;
import ru.itis.zheleznov.services.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String getPage() {
        return "signup_view";
    }

    @PostMapping("/signUp")
    public String signUp(SignUp model) {
        System.out.println(model);
        //TODO обрабатывать русские символы
        if (userService.signUp(model)) {
            System.out.println("cool");
        }
        return "redirect:/signUp";
    }
}
