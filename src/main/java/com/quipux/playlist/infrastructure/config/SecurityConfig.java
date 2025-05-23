package com.quipux.playlist.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuración de seguridad para la aplicación de playlists.
 * Configuración básica para desarrollo con H2 Console habilitada.
 * Actualizada para Spring Security 6.1+
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configuración del filtro de seguridad principal.
     * Para desarrollo: permite acceso a H2 Console y APIs sin autenticación.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF para APIs REST
                .csrf(csrf -> csrf.disable())

                // Configuración de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Configuración de autorización
                .authorizeHttpRequests(authz -> authz
                        // Permitir acceso a H2 Console
                        .requestMatchers("/h2-console/**").permitAll()
                        // Permitir acceso a todas las APIs (desarrollo)
                        .requestMatchers("/lists/**").authenticated()
                        // Cualquier otra request requiere autenticación
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()) // <- Aquí se habilita HTTP Basic
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                        .contentTypeOptions(contentType -> {})
                        .httpStrictTransportSecurity(hsts -> hsts
                                .maxAgeInSeconds(31536000)
                                .includeSubDomains(true)
                        )
                        .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                        .cacheControl(cache -> {})
                );

        return http.build();
    }

    /**
     * Configuración de CORS para permitir requests desde frontend.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Orígenes permitidos (para desarrollo)
        configuration.setAllowedOriginPatterns(List.of("*"));

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));

        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization", "Cache-Control", "Content-Type", "Accept",
                "X-Requested-With", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Headers", "Origin"
        ));

        // Headers expuestos
        configuration.setExposedHeaders(Arrays.asList(
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Location"
        ));

        // Permitir credenciales
        configuration.setAllowCredentials(true);

        // Tiempo de cache para preflight requests
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}