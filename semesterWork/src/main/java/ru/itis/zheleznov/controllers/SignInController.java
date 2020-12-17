package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.itis.zheleznov.models.SignIn;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class SignInController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signIn")
    public String getPage() {
        return "signin_view";
    }

    @PostMapping("/signIn")
    public String authentication(@Valid SignIn signIn, BindingResult bindingResult, Model model, HttpServletResponse response) {
        //TODO добавить валидацию
        if (userService.singIn(signIn)) {
            model.addAttribute("user", userService.getUser(signIn.getEmail()));
            if(signIn.isRemember()) {
                //TODO иметь две куки или одну?
                Cookie name = new Cookie("email", signIn.getEmail());
                name.setMaxAge(60*60*24);
                Cookie hash = new Cookie("hash", passwordEncoder.encode(signIn.getPassword()));
                hash.setMaxAge(60*60*24);
                response.addCookie(name);
                response.addCookie(hash);
            }
            return "redirect:/profile";
        }
        //TODO вывести ошибки
        return "redirect:/signIn";
    }
}
