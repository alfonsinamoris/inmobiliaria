package com.example.demo.security; // O el paquete donde lo tengas

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/", "/publica/**", "/css/**", "/img/**").permitAll() // 1. Permitir lo público
        .requestMatchers("/admin/**").hasRole("ADMIN")                         // 2. Proteger lo privado
        .anyRequest().authenticated()                                          // 3. Todo lo demás requiere login
    )
            .formLogin((form) -> form
                .loginPage("/login") // Tu página personalizada
                .defaultSuccessUrl("/admin/dashboard", true) // A donde va tras loguearse
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // Tras salir va al home público
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
            .password(passwordEncoder().encode("1234"))
            .roles("ADMIN") // Spring le agrega "ROLE_" automáticamente -> ROLE_ADMIN
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}