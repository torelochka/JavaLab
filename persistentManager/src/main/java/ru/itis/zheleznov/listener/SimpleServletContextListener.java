package ru.itis.zheleznov.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.zheleznov.repositories.UserRepository;
import ru.itis.zheleznov.repositories.UserRepositoryJdbcImpl;

import javax.servlet.ServletContextEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.zheleznov.services.SignInService;
import ru.itis.zheleznov.services.SignInServiceJdbsImpl;
import ru.itis.zheleznov.services.UserService;
import ru.itis.zheleznov.services.UserServiceJdbcImpl;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class SimpleServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();

        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            throw  new IllegalArgumentException(e);
        }

        //        DB

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        config.setUsername(properties.getProperty("db.jdbc.username"));
        config.setPassword(properties.getProperty("db.jdbc.password"));
        config.setDriverClassName(properties.getProperty("db.jdbc.driver.classname"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));

        HikariDataSource dataSource = new HikariDataSource(config);

        //    Repositories

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserServiceJdbcImpl(userRepository);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SignInService signInService = new SignInServiceJdbsImpl(userRepository, passwordEncoder);
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("passwordEncoder", passwordEncoder);
        sce.getServletContext().setAttribute("signInService", signInService);
    }
}
