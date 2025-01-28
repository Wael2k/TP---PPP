package com.example.examenprincipalewaelboussoffara.security;

import com.example.examenprincipalewaelboussoffara.security.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ApplicationConfig {
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }




}
