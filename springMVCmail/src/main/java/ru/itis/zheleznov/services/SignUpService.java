package ru.itis.zheleznov.services;

import ru.itis.zheleznov.models.SignUpForm;

import javax.servlet.http.HttpSession;

public interface SignUpService {
    void signUp(SignUpForm form,  HttpSession session);
}
