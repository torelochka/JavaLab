package ru.itis.zheleznov.filters;

import ru.itis.zheleznov.services.AuthService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        boolean user = req.getSession().getAttribute("user") != null;


        if (user) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        resp.sendRedirect("/login");
    }
}
