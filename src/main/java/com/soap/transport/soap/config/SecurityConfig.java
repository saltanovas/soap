package com.soap.transport.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws/**").authenticated()
                        .anyRequest().permitAll()
                )
                // TODO: configure exceptionHandling to have a custom 401 & 403 response for SOAP requests
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails reader = User.withUsername("user")
                .password("{noop}user")
                .roles("READ")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("READ", "WRITE")
                .build();

        return new InMemoryUserDetailsManager(reader, admin);
    }
}
