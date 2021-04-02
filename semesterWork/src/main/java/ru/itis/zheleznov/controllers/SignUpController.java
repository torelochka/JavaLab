package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.zheleznov.dto.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.SignUpService;
import ru.itis.zheleznov.services.UserService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Controller
public class SignUpController {

    private final SignUpService signUpService;
    private final UserService userService;

    @Autowired
    public SignUpController(SignUpService signUpService, UserService userService) {
        this.signUpService = signUpService;
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String signUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @PostMapping("/signUp")
    public String register(@Valid SignUpForm form, BindingResult bindingResult, Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                System.out.println(Arrays.toString(error.getCodes()));
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.ValidPasswords")) {
                    model.addAttribute("passwordsErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("signUpForm", form);
            return "signup";
        }

        User user = signUpService.signUp(form);
        if (user != null) {
            return "redirect:/signIn";
        }

        return "redirect:/signUp";
    }
}