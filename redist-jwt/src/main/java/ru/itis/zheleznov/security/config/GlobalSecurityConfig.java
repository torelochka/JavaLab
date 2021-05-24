package ru.itis.zheleznov.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.zheleznov.security.jwt.JwtLogoutFilter;
import ru.itis.zheleznov.security.jwt.TokenAuthenticationFilter;
import ru.itis.zheleznov.security.jwt.TokenAuthenticationProvider;


@EnableWebSecurity
public class GlobalSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtLogoutFilter, LogoutFilter.class)
                .csrf().disable()
                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers("/test", "/logout").authenticated()
                .antMatchers("/ban/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/auth/**", "/api/cities").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

}
