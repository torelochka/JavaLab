package ru.itis.zheleznov;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.zheleznov.utils.ConfigParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HikariConfig hikariConfig = new HikariConfig();
        Map<String, String> configDB = ConfigParser.parseDBConfig();

        hikariConfig.setJdbcUrl(configDB.get("URL"));
        hikariConfig.setDriverClassName(configDB.get("DRIVER"));
        hikariConfig.setUsername(configDB.get("USERNAME"));
        hikariConfig.setPassword(configDB.get("PASS"));
        hikariConfig.setMaximumPoolSize(10);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        sce.getServletContext().setAttribute("datasource", dataSource);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("datasource", null);
    }

}
