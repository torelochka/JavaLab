package ru.itis.zheleznov.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.itis.zheleznov.models.SignIn;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class ProfileFilter implements Filter {

    @Autowired
    private UserService userService;

    private WebApplicationContext springContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    // https://github.com/LegendaryZer0/JavaEE-itis-lab
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println(userService);
        if (req.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String email = null;
            String hash = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                } else if (cookie.getName().equals("hash")) {
                    hash = cookie.getValue();
                }
            }
            //TODO удаление кук
            if (email != null && hash != null &&
                    userService.singIn(SignIn.builder()
                            .email(email)
                            .password(hash)
                            .build())) {
                doFilter(request, response, chain);
            }
        }
        resp.sendRedirect("/");
    }
}
