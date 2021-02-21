package ru.itis.zheleznov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.models.Email;
import ru.itis.zheleznov.models.SignUpForm;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.repositories.UsersRepository;
import ru.itis.zheleznov.utils.EmailUtil;
import ru.itis.zheleznov.utils.MailsGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;

    private final EmailUtil emailUtil;

    private final MailsGenerator mailsGenerator;

    @Autowired
    public SignUpServiceImpl(UsersRepository usersRepository, EmailUtil emailUtil, MailsGenerator mailsGenerator) {
        this.usersRepository = usersRepository;
        this.emailUtil = emailUtil;
        this.mailsGenerator = mailsGenerator;
    }
    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void signUp(SignUpForm form, HttpSession session) {
        User newUser = User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .confirmedCode(UUID.randomUUID())
                .build();

        if (usersRepository.save(newUser)) {
            session.setAttribute("authenticated", true);
            String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmedCode().toString());
            Email email = new Email(from, newUser.getEmail(), "Регистрация", confirmMail);
            emailUtil.sendMail(email);
        }

    }

}
