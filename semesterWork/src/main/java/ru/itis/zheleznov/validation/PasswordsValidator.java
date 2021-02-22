package ru.itis.zheleznov.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class PasswordsValidator implements ConstraintValidator<ValidPasswords, Object> {

    private String passwordPropertyName;
    private String passwordAgainPropertyName;

    @Override
    public void initialize(ValidPasswords constraintAnnotation) {
        this.passwordPropertyName = constraintAnnotation.password();
        this.passwordAgainPropertyName = constraintAnnotation.passwordAgain();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(o).getPropertyValue(passwordPropertyName);
        Object passwordAgain = new BeanWrapperImpl(o).getPropertyValue(passwordAgainPropertyName);

        return password.equals(passwordAgain);
    }
}
