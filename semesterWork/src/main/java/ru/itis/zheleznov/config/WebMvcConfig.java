package ru.itis.zheleznov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import ru.itis.zheleznov.interceptors.CookieInterceptor;
import ru.itis.zheleznov.interceptors.SecurityInterceptor;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.UserService;

import java.util.Locale;

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
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
        return codesResolver;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("localeInfo");
        cookieLocaleResolver.setCookieMaxAge(60 * 60 * 24 * 365);
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/localization");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
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
        registry.addInterceptor(localeChangeInterceptor());
        //registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/main", "/signIn", "/signUp");
    }
}
