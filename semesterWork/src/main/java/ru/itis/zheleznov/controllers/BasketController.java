package ru.itis.zheleznov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasketController {

    private final ProductService productService;
    private final BasketService basketService;

    public BasketController(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
    }

    @GetMapping("/basket")
    public String basketPage(HttpServletRequest req) {
        System.out.println(req.getSession().getAttribute("basket"));
        return "basket";
    }

    @GetMapping("/basketService/add/{id}")
    public String addProduct(@PathVariable long id, HttpServletRequest req) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        basketService.addProductInBasket(basket, product);
        List<Product> productList = basket.getProducts();
        productList.add(product);
        basket.setProducts(productList);
        return "/services";
    }

    @GetMapping("/basketService/delete/{id}")
    public String deleteProduct(@PathVariable long id, HttpServletRequest req) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) req.getSession().getAttribute("basket");
        basketService.deleteProductFromBasket(basket, product);
        List<Product> productList = basket.getProducts();
        productList.remove(product);
        basket.setProducts(productList);
        return "/basket";
    }
}
