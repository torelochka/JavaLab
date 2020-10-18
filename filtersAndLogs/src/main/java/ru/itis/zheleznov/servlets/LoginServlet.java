package ru.itis.zheleznov.servlets;

import ru.itis.zheleznov.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        boolean find = true;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("AUTH")) {
                find = false;
            }
        }

        if (find) {
            AuthService authService = (AuthService) req.getServletContext().getAttribute("authService");

            Cookie cookie = new Cookie("AUTH", UUID.randomUUID().toString());
            cookie.setMaxAge(60 * 60 * 24 * 365);
            authService.addCookieAuth(cookie);
            resp.addCookie(cookie);
            resp.getWriter().write("now you register");
        } else {
            resp.sendRedirect("/profile");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
