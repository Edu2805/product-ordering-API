package com.springbootexpert.vendas.config;

import com.springbootexpert.vendas.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    public UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/client/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/purchase/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/product/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
