/*
package ru.itis.zheleznov.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        UsersService usersService = (UsersService) req.getServletContext().getAttribute("userService");

        BasketService basketService = (BasketService) req.getServletContext().getAttribute("basketService");

        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            setBasket(user, basketService, req);
            return;
        }

        String email = "";
        String password = "";

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                email = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        if (!email.equals("") && !password.equals("")) {
            user = usersService.getUserByEmailPassword(email, password);
            if (user != null) {
                if (user.getProfileImg() != null) {
                    user.setImage(FileToImage.toImage(user.getProfileImg()));
                }
                req.getSession().setAttribute("user", user);
                setBasket(user, basketService, req);
            } else {
                CookieActions.deleteCookies(req, resp, "email", "password");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
*/
