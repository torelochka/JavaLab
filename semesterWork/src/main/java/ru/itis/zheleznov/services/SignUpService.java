package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.SignUpForm;
import ru.itis.zheleznov.models.User;

import javax.servlet.http.HttpSession;

public interface SignUpService {
    User signUp(SignUpForm form);
}
