package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Default;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Default
public class SignUpForm {
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String passwordAgain;
}
