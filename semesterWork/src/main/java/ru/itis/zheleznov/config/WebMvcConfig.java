package ru.itis.zheleznov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import ru.itis.zheleznov.interceptors.CookieInterceptor;
import ru.itis.zheleznov.interceptors.CsrfInterceptor;
import ru.itis.zheleznov.interceptors.SecurityInterceptor;
import ru.itis.zheleznov.services.UserService;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private UserService userService;

    public WebMvcConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/profile", "/basket", "/services");
        registry.addInterceptor(new CookieInterceptor(userService));
        //registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/main", "/signIn", "/signUp");
    }
}
