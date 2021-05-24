package ru.itis.zheleznov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.zheleznov.services.UserService;

@RestController
public class TestJwtController {

    private final UserService userService;

    @Autowired
    public TestJwtController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/ban/{id}")
    public void test(@PathVariable Long id) {
        userService.banUser(id);
    }
}
