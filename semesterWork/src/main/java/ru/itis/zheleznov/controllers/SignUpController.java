package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.zheleznov.models.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping("/signUp")
    public String register(SignUpForm form, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (userService.userIsExist(form.getEmail())) {
            /*this.error = "Такой пользователь уже существует";*/
            return "redirect:/signUp";
        } else if (form.getPassword().equals(form.getPasswordAgain())){
            userService.addUser(form);
            Optional<User> user = userService.getUserByEmail(form.getEmail());
            if (user.isPresent()) {
                req.getSession().setAttribute("id", user.get().getId());
                req.getSession().setAttribute("user", user.get());
            }
            /*this.error = null;*/
            req.getSession().setAttribute("authenticated", true);
            return "redirect:/profile";
        } else {
            return "redirect:/signUp";
        }
    }
}
