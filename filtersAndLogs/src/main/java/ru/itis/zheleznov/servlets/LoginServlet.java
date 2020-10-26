package ru.itis.zheleznov.servlets;

import ru.itis.zheleznov.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean user = session.getAttribute("user") != null;

        if (!user) {
            session.setAttribute("user", true);
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
