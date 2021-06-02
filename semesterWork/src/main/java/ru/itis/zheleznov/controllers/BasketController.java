package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.zheleznov.aspects.LogExecutionTime;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.security.details.UserDetailsImpl;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasketController {

    private final ProductService productService;
    private final BasketService basketService;

    @Autowired
    public BasketController(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
    }

    @GetMapping("/basket")
    public String basketPage(HttpServletRequest req, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Basket basket = basketService.createOrGetBasket(userDetails.getUser());
        System.out.println(basket);
        model.addAttribute("basket", basket);
        return "basket";
    }

    @GetMapping("/basketService/add/{id}")
    public String addProduct(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Product product = productService.getProductById(id);
        User user = userDetails.getUser();
        Basket basket = basketService.createOrGetBasket(user);
        basketService.addProductInBasket(basket, product);
        return "redirect:/services";
    }

    @GetMapping("/basketService/delete/{id}")
    public String deleteProduct(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Product product = productService.getProductById(id);

        User user = userDetails.getUser();
        Basket basket = basketService.createOrGetBasket(user);

        basketService.deleteProductFromBasket(basket, product);
        return "redirect:/basket";
    }
}
