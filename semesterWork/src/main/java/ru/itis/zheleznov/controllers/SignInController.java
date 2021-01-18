package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.services.SignInService;

import javax.jws.WebParam;

@Controller
public class SignInController {

    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @GetMapping("/signIn")
    public String signInPage() {
            return "signin";
    }

    @PostMapping("/signIn")
    public String signIn(SignInForm form, @RequestParam("remember") String remember, Model model) {
        form.setRememberMe(Boolean.getBoolean(remember));
        if (signInService.authenticate(form)) {
            return "redirect:/profile";
        }
        return "redirect:/signIn";
    }
}
