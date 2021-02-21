package ru.itis.zheleznov.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class TestServiceImpl2 implements TestService {

    @Override
    public String getString() {

        return "bye world";
    }
}
