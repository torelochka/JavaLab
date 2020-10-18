package ru.itis.zheleznov.filters;

import ru.itis.zheleznov.services.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        AuthService authService = (AuthService) req.getServletContext().getAttribute("authService");

        Cookie[] cookies = req.getCookies();
        boolean find = false;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("AUTH")) {
                find = authService.findCookieAuth(cookie);
                break;
            }
        }

        if (find) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
