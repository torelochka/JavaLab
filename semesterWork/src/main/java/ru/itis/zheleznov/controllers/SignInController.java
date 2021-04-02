package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.zheleznov.dto.SignInForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.SignInService;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String signInPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "signin";
    }

}
