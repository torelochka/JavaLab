package ru.itis.zheleznov.servlets;

import ru.itis.zheleznov.filters.ResponseUtil;
import ru.itis.zheleznov.models.SignInForm;
import ru.itis.zheleznov.services.SignInService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signIn.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        SignInService signInService = (SignInService) req.getServletContext().getAttribute("signInService");

        if (signInService.authenticate(new SignInForm(email, password))) {
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            String redirect = req.getParameter("redirect");

            if (redirect == null) {
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect(redirect);
            }
        } else {
            ResponseUtil.sendForbidden(req, resp);
        }
    }
}
