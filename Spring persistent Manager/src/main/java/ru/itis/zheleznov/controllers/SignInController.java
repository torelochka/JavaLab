package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.itis.zheleznov.interceptors.ResponseUtil;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.services.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static ru.itis.zheleznov.interceptors.ResponseUtil.sendForbidden;

@Controller
public class SignInController {

    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @GetMapping("/signIn")
    public String signInPage() {
        return "signIn_page";
    }

    @PostMapping("/signIn")
    public String authorization(SignInForm form, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (signInService.authenticate(form)) {
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            String redirect = req.getParameter("redirect");

            if (redirect == null) {
                return "redirect:/profile";
            } else {
                return "redirect:/" + redirect;
            }
        }
        sendForbidden(req, resp);
        return "";
    }

}
