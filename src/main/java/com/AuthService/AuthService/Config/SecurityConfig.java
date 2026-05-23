package com.AuthService.AuthService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 🔐 Spring creates this object and injects wherever needed
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 🔓 disable CSRF for REST APIs
                .csrf(csrf -> csrf.disable())

                // 🔓 allow public APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/signup", "/auth/login").permitAll()

                        // 🔴 ADMIN only can ADD quiz
                        .requestMatchers("/quiz/add").hasRole("ADMIN")

                        // 🟡 BOTH ADMIN + USER can GET quiz
                        .requestMatchers("/quiz/get").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
