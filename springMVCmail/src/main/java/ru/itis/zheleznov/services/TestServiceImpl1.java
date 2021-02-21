package ru.itis.zheleznov.services;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("master")
public class TestServiceImpl1 implements TestService {

    @Override
    public String getString() {
        return "hello world";
    }
}
