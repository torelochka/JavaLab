package ru.itis.zheleznov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import ru.itis.zheleznov.interceptors.CookieInterceptor;
import ru.itis.zheleznov.interceptors.CsrfInterceptor;
import ru.itis.zheleznov.interceptors.SecurityInterceptor;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.UserService;

import java.util.ArrayList;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserService userService;
    private final BasketService basketService;

    public WebMvcConfig(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
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
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/profile", "/basket", "/services", "/basketService", "/purchase");
        registry.addInterceptor(new CookieInterceptor(userService, basketService));
        //registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/main", "/signIn", "/signUp");
    }
}
