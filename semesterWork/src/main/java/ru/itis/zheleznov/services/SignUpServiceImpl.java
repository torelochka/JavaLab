package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Email;
import ru.itis.zheleznov.models.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UserRepository;
import ru.itis.zheleznov.utils.EmailUtil;
import ru.itis.zheleznov.utils.MailsGenerator;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository usersRepository;

    private final EmailUtil emailUtil;

    private final MailsGenerator mailsGenerator;

    private final PasswordEncoder encoder;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, EmailUtil emailUtil, MailsGenerator mailsGenerator, PasswordEncoder encoder) {
        this.usersRepository = userRepository;
        this.emailUtil = emailUtil;
        this.mailsGenerator = mailsGenerator;
        this.encoder = encoder;
    }
    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void signUp(SignUpForm form, HttpSession session) {
        User newUser = User.builder()
                .name(form.getFirstname())
                .lastname(form.getLastname())
                .email(form.getEmail())
                .confirmedCode(UUID.randomUUID())
                .passwordHash(encoder.encode(form.getPassword()))
                .build();

        if (usersRepository.save(newUser)) {
            System.out.println("true");
            session.setAttribute("authenticated", true);
            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmedCode().toString());
            Email email = new Email(from, newUser.getEmail(), "Регистрация", confirmMail);
            emailUtil.sendMail(email);
        }

    }

}
