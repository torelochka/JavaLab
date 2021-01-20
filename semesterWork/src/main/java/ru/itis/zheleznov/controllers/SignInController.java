package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.SignInService;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SignInController {

    private final SignInService signInService;
    private final UserService userService;

    public SignInController(SignInService signInService, UserService userService) {
        this.signInService = signInService;
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String signInPage() {
            return "signin";
    }

    @PostMapping("/signIn")
    public String signIn(SignInForm form, @RequestParam("remember") String remember, HttpServletRequest request) {
        form.setRememberMe(Boolean.getBoolean(remember));
        if (signInService.authenticate(form)) {
            // сохранять в куки?
            Optional<User> user = userService.getUserByEmail(form.getEmail());
            if (user.isPresent()) {
                request.getSession().setAttribute("id", user.get().getId());
                request.getSession().setAttribute("user", user.get());
            }
            request.getSession().setAttribute("authenticated", true);
            return "redirect:/profile";
        }
        return "redirect:/signIn";
    }
}
