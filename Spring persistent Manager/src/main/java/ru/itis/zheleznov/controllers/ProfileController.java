package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profilePage() {
        return "profile_page";
    }

    @GetMapping("/quit")
    public String quit(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.setAttribute("authenticated", false);
        }
        return "redirect:/";
    }
}
