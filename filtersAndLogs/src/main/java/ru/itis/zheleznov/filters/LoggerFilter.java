package ru.itis.zheleznov.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.zheleznov.logger.Slf4jLogbackLogger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebFilter("/*")
public class LoggerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(
            Slf4jLogbackLogger.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        logger.info("connect to: " + req.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
