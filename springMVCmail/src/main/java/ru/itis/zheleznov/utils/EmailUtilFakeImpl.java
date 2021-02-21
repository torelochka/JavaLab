package ru.itis.zheleznov.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.zheleznov.models.Email;

@Component
@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {

    @Override
    public void sendMail(Email email) {
        System.out.println(email.getBody());
    }
}
