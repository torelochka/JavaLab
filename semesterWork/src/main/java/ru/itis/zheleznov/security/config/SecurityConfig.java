package ru.itis.zheleznov.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.itis.zheleznov.filters.EncodingFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/services").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/basket").authenticated()
                .antMatchers("/basketService/**").authenticated()
                .and()
                .csrf()
                .ignoringAntMatchers("/search", "/service")
                .and()
                .addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class)
                .formLogin()
                .loginPage("/signIn")
                .usernameParameter("email")
                .defaultSuccessUrl("/profile")
                .failureUrl("/signIn?error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ;
    }
}
