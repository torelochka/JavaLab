package ru.itis.zheleznov.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.zheleznov.validation.ValidPassword;
import ru.itis.zheleznov.validation.ValidPasswords;

import javax.enterprise.inject.Default;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Default
@ValidPasswords(
        message = "{errors.incorrect.passwordsMatch}",
        password = "password",
        passwordAgain = "passwordAgain"
)

public class SignUpForm {
    private String lastname;
    private String firstname;
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.incorrect.password}")
    private String password;
    private String passwordAgain;
}
