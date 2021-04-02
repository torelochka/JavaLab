package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.Email;
import ru.itis.zheleznov.dto.SignUpForm;
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
    public User signUp(SignUpForm form) {
        User newUser = User.builder()
                .firstname(form.getFirstname())
                .lastname(form.getLastname())
                .email(form.getEmail())
                .confirmedCode(UUID.randomUUID())
                .passwordHash(encoder.encode(form.getPassword()))
                .build();

        if (!usersRepository.findByEmail(newUser.getEmail()).isPresent()) {
            User user = usersRepository.save(newUser);
            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmedCode().toString());
            Email email = new Email(from, newUser.getEmail(), "Регистрация", confirmMail);
            emailUtil.sendMail(email);
            return user;
        }
        return null;
    }

}
