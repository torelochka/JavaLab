package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.security.details.UserDetailsImpl;
import ru.itis.zheleznov.services.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final PurchaseService purchaseService;

    @Autowired
    public ProfileController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, HttpServletRequest req) {
        User user = userDetails.getUser();
        req.getSession().setAttribute("user", user);
        model.addAttribute("purchases", purchaseService.getUserPurchase(user));
        model.addAttribute("user", user);
        return "profile";
    }
}
