package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/quit")
    public String quit(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("id", null);
        req.getSession().setAttribute("authenticated", false);
        return "main";
    }
}
