package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.zheleznov.models.Product;
import ru.itis.zheleznov.dto.SearchRequest;
import ru.itis.zheleznov.services.ProductService;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicesController {

    private final ProductService productService;

    @Autowired
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
        List<Product> productsBySearchRequest = productService.getProductsBySearchRequest(searchRequest);
        return productsBySearchRequest;
    }

    @GetMapping(value = "/service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> autocompleteRequest() {
        List<Product> products = productService.allProducts();
        List<String> productsName = new ArrayList<>();
        for (Product product : products) {
            productsName.add(product.getName());
        }
        return productsName;
    }
}
