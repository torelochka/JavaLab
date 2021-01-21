package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public String buyProducts(HttpServletRequest req) {
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        User user = (User) req.getSession().getAttribute("user");

        if (basket != null) {
            List<Product> products = basket.getProducts();
            if (!products.isEmpty()) {
                Purchase purchase = Purchase.builder().basketId(basket.getId()).products(products).customer(user).build();
                purchaseService.addPurchase(purchase);
                List<Product> purchases = purchaseService.getUserPurchase(user);
                req.getSession().setAttribute("purchases", purchases);
                basket.setProducts(new ArrayList<>());
                return "redirect:/profile";
            } else {
                return "redirect:/basket?error=empty";
            }
        } else {
            return "redirect:/basket";
        }
    }
}
