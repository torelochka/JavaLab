package ru.itis.zheleznov.servlets;

import ru.itis.zheleznov.repositories.UsersRepository;
import ru.itis.zheleznov.repositories.UsersRepositoryJdbcImpl;
import ru.itis.zheleznov.services.UsersService;
import ru.itis.zheleznov.services.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        this.usersService = new UsersServiceImpl(usersRepository);
        System.out.println(usersService.getAllUsers());
        System.out.println(usersService.getAllUsersByName("torelochka"));
    }
}
