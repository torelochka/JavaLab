package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zheleznov.repositories.CategoryRepository;

@Controller
public class MainController {

    private final CategoryRepository categoryRepository;

    public MainController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("categories", categoryRepository.getAll());
        return "main";
    }

}
