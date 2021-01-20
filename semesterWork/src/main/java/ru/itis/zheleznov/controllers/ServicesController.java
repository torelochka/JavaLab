package ru.itis.zheleznov.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.models.SearchRequest;
import ru.itis.zheleznov.repositories.ProductRepository;
import ru.itis.zheleznov.services.ProductService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicesController {

    private final ProductService productService;

    public ServicesController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/services")
    public String getServicesPage(Model model) {
        return "services";
    }

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> getSearchPage(@RequestBody SearchRequest searchRequest) {
        return  productService.getProductsBySearchRequest(searchRequest);
    }

    @GetMapping(value = "/service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> autocompleteRequest() {
        List<Product> products = productService.allProducts();
        List<String> productsName = new ArrayList<>();
        for (Product product: products) {
            productsName.add(product.getName());
        }
        return productsName;
    }
}
