package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    private final PurchaseService purchaseService;

    public ProfileController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/profile")
    public String profilePage(HttpServletRequest req) {
        List<Product> purchases = (List<Product>) req.getSession().getAttribute("purchases");
        if (purchases == null) {
            User user = (User) req.getSession().getAttribute("user");
            req.getSession().setAttribute("purchases", purchaseService.getUserPurchase(user));
        }
        return "profile";
    }

    @GetMapping("/quit")
    public String quit(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("id", null);
        req.getSession().setAttribute("authenticated", false);
        return "redirect:/main";
    }
}
