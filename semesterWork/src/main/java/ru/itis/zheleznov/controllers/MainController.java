package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.security.details.UserDetailsImpl;
import ru.itis.zheleznov.services.CategoryService;


@Controller
public class MainController {

    private final CategoryService categoryService;

    @Autowired
    public MainController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "main";
    }
}
