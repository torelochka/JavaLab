package ru.itis.zheleznov.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.zheleznov.config.ApplicationConfig;

import javax.servlet.*;
import java.io.IOException;
import java.util.EnumSet;

@Configuration
public class SpringWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();

        try {
            PropertySource propertySource = new ResourcePropertySource("classpath:profile.properties");
            appContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("string.profile"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);

        appContext.register(ApplicationConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
                new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");
        cef.setForceEncoding(true);
        FilterRegistration.Dynamic characterEncoder = servletContext.addFilter("encodingFilter", cef);
        characterEncoder.setInitParameter("encoding", "UTF-8");
        characterEncoder.setInitParameter("forceEncoding", "true");
        characterEncoder.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        characterEncoder.setAsyncSupported(true);
    }
}
