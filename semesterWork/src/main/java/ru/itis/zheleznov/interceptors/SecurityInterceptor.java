package ru.itis.zheleznov.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (!isProtected(request.getRequestURI())) {
            if (session != null) {
                Boolean authenticated = (Boolean) session.getAttribute("authenticated");
                if (authenticated != null && authenticated) {
                    return true;
                } else {
                    response.sendRedirect("/signIn");
                    return false;
                }
            } else {
                response.sendRedirect("/signIn");
                return false;
            }
        } else if (session != null) {
            Boolean authenticated = (Boolean) session.getAttribute("authenticated");
            if (authenticated != null && authenticated) {
                response.sendRedirect("/");
            }
            return true;
        } else {
            return true;
        }
    }

    private boolean isProtected(String path) {
        return path.startsWith("/signIn") || path.startsWith("/signUp") || path.equals("/favicon.ico");
    }
}
