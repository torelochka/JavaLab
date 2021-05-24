package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.SignUpForm;

public interface SignUpService {
    Boolean signUp(SignUpForm signUpForm);
}
