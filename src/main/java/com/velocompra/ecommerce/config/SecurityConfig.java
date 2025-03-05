package com.velocompra.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs RESTful
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // Permite acesso ao endpoint de login
                        .requestMatchers("/public/**").permitAll() // Permite acesso a endpoints públicos
                        .requestMatchers("/backoffice/**").hasAnyRole("ADMINISTRADOR", "ESTOQUISTA") // Exige autenticação para backoffice
                        .anyRequest().authenticated() // Exige autenticação para outros endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Desabilita autenticação básica
                .formLogin(form -> form.disable()) // Desabilita o formulário de login
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout") // Configura o endpoint de logout
                        .logoutSuccessUrl("/login") // Redireciona após logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}