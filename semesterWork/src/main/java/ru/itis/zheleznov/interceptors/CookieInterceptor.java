package ru.itis.zheleznov.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.zheleznov.models.Basket;
import ru.itis.zheleznov.models.User;
import ru.itis.zheleznov.services.BasketService;
import ru.itis.zheleznov.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final BasketService basketService;

    public CookieInterceptor(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Boolean authenticated = (Boolean) session.getAttribute("authenticated");
            if (authenticated != null && authenticated) {
                User user = (User) session.getAttribute("user");
                Basket basket = (Basket) session.getAttribute("basket");
                Long id = (Long) session.getAttribute("id");
                if (user == null && id != null) {
                    user = userService.getUserById(id);
                    session.setAttribute("user", user);
                }
                if (basket == null) {
                    basket = basketService.createOrGetBasket(user);
                    session.setAttribute("basket", basket);
                }
            }
        }
        return true;
    }
}

