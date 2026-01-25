package com.backend.happyhome.configuration.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Enable CORS
            .cors(Customizer.withDefaults())

            // Disable CSRF for APIs (important for React + Axios)
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // 🔓 PUBLIC ENDPOINTS
                .requestMatchers(
                    "/payments/**",
                    "/auth/**",
                    "/login",
                    "/error"
                ).permitAll()

                // 🔒 EVERYTHING ELSE
                .anyRequest().permitAll()
            )

            // ❌ Disable default login page redirect
            .formLogin(form -> form.disable())

            // Optional: enable basic auth for testing
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

   
}
