package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.Purchase;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.BasketRepository;
import ru.itis.zheleznov.security.details.UserDetailsImpl;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;

    private final BasketService basketService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, BasketService basketService) {
        this.purchaseService = purchaseService;
        this.basketService = basketService;
    }

    @GetMapping("/purchase")
    public String buyProducts(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        User user = userDetails.getUser();
        Basket basket = basketService.getUserBasket(user).orElseThrow(() -> new UsernameNotFoundException("basket not found"));

        List<Product> products = basket.getProducts();
        if (!products.isEmpty()) {
            Purchase purchase = Purchase.builder()
                    .products(products)
                    .customer(user)
                    .build();
            purchaseService.save(purchase);

            model.addAttribute("purchases", purchaseService.getUserPurchase(user));

            return "redirect:/profile";
        }

        return "redirect:/basket?error=empty";
    }
}
