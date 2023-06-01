package com.nebrija.tfg.qrnotify.notifications.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomJwtRequestFilter customJwtRequestFilter;
    @Bean
    SecurityFilterChain userSecurityfilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .antMatcher("/nebrija/qrnotify-notifications/user/**")
                .addFilterBefore(customJwtRequestFilter, OAuth2LoginAuthenticationFilter.class)
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated())// validates access tokens as JWTs
                .build();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests((authorize) -> authorize
                        .antMatchers("/nebrija/qrnotify-notifications/user/**","/nebrija/qrnotify-notifications/test","/nebrija/qrnotify-notifications/login", "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**", "/configuration/**")
                        .permitAll()
                        .anyRequest().authenticated()) // All requests require authentication
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // validates access tokens as JWTs
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  // Permite todos los orígenes
        configuration.setAllowedMethods(Arrays.asList("*"));  // Permite todos los métodos (GET, POST, etc.)
        configuration.setAllowedHeaders(Arrays.asList("*"));  // Permite todos los encabezados
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
