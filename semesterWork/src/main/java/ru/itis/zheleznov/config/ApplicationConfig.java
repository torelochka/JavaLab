package ru.itis.zheleznov.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ScriptStatementFailedException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.zheleznov.repositories.*;
import ru.itis.zheleznov.services.*;

import javax.sql.DataSource;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableWebMvc
@PropertySource("classpath:app.properties")
@ComponentScan("ru.itis.zheleznov")
@EnableJdbcHttpSession
public class ApplicationConfig extends AbstractHttpSessionApplicationInitializer {

    @Autowired
    private Environment environment;

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));

        mailSender.setDefaultEncoding("UTF-8");

        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", environment.getProperty("spring.mail.properties.transport.protocol"));
        props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

        return mailSender;
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        return freeMarkerConfigurer().getConfiguration();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @SneakyThrows
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        ClassPathResource classPathResource = new ClassPathResource("org/springframework/session/jdbc/schema-postgresql.sql");

        EncodedResource resource = new EncodedResource(classPathResource, "UTF-8");
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        } catch (ScriptStatementFailedException e) {
            //empty
        }
        return new DataSourceTransactionManager(dataSource);
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
        configurer.setDefaultEncoding("UTF-8");
        configurer.setTemplateLoaderPath("WEB-INF/views/templates/");
        return configurer;
    }
}
