package com.example.userscrud.configurations;

import com.example.userscrud.configurations.filters.ApplicationAuthenticationFilter;
import com.example.userscrud.configurations.filters.JwtAuthorizationFilter;
import com.example.userscrud.configurations.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthorizationFilter authorizationFilter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        ApplicationAuthenticationFilter authenticationFilter = new ApplicationAuthenticationFilter(jwtConfig, secretKey);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/application/access");

        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)
        );
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.addFilter(authenticationFilter)
                .addFilterBefore(authorizationFilter, ApplicationAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/token/refresh").permitAll();
            auth.requestMatchers("/users/create").permitAll();
            auth.requestMatchers("/users/**").hasRole("ADMIN");
        });
        return http.httpBasic(Customizer.withDefaults()).build();
    }
}
