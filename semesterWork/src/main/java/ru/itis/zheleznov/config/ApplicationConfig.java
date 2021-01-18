package ru.itis.zheleznov.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.zheleznov.repositories.*;
import ru.itis.zheleznov.services.SignInService;
import ru.itis.zheleznov.services.SignInServiceJdbsImpl;
import ru.itis.zheleznov.services.UserService;
import ru.itis.zheleznov.services.UserServiceJdbcImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@PropertySource("classpath:db.properties")
@ComponentScan("ru.itis.zheleznov")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryJdbcImpl(jdbcTemplate());
    }

    @Bean
    ProductRepository productRepository() { return new ProductRepositoryJdbcImpl(jdbcTemplate()); }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SignInService signInService() { return new SignInServiceJdbsImpl(userService(), passwordEncoder()); }

    @Bean
    public UserService userService() {
        return new UserServiceJdbcImpl(userRepository());
    }

    @Bean
    BasketRepository basketRepository() { return new BasketRepositoryJdbcImpl(jdbcTemplate(), userRepository()); }

    @Bean
    PurchaseRepository purchaseRepository() { return new PurchaseRepositoryJdbcImpl(jdbcTemplate(), productRepository(), basketRepository()); }

    @Bean
    CategoryRepository categoryRepository() { return new CategoryRepositoryJdbcImpl(jdbcTemplate()); }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.jdbc.url"));
        config.setUsername(environment.getProperty("db.jdbc.username"));
        config.setPassword(environment.getProperty("db.jdbc.password"));
        config.setDriverClassName(environment.getProperty("db.jdbc.driver.classname"));
        config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.jdbc.hikari.max-pool-size")));
        return config;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("WEB-INF/views/templates/");
        return configurer;
    }
}
