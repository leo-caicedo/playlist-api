package com.quipux.playlist.infrastructure.config;

import com.quipux.playlist.application.service.PlaylistApplicationService;
import com.quipux.playlist.application.usecase.CreatePlaylistUseCase;
import com.quipux.playlist.application.usecase.DeletePlaylistUseCase;
import com.quipux.playlist.application.usecase.GetAllPlaylistsUseCase;
import com.quipux.playlist.application.usecase.GetPlaylistByNameUseCase;
import com.quipux.playlist.domain.repository.PlaylistRepository;
import com.quipux.playlist.domain.service.PlaylistDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.quipux.playlist.infrastructure.persistence")
@ConfigurationPropertiesScan(basePackages = "com.quipux.playlist")
public class ApplicationConfig implements WebMvcConfigurer {

    /**
     * Bean del servicio de dominio.
     * Contiene la lógica de negocio pura y requiere el repositorio.
     */
    @Bean
    public PlaylistDomainService playlistDomainService(PlaylistRepository playlistRepository) {
        return new PlaylistDomainService(playlistRepository);
    }

    // ========== USE CASES BEANS ==========

    /**
     * Use Case para crear playlists.
     */
    @Bean
    public CreatePlaylistUseCase createPlaylistUseCase(
            PlaylistRepository playlistRepository,
            PlaylistDomainService playlistDomainService) {
        return new CreatePlaylistUseCase(playlistRepository, playlistDomainService);
    }

    /**
     * Use Case para obtener todas las playlists.
     */
    @Bean
    public GetAllPlaylistsUseCase getAllPlaylistsUseCase(PlaylistRepository playlistRepository) {
        return new GetAllPlaylistsUseCase(playlistRepository);
    }

    /**
     * Use Case para obtener una playlist por nombre.
     */
    @Bean
    public GetPlaylistByNameUseCase getPlaylistByNameUseCase(PlaylistRepository playlistRepository) {
        return new GetPlaylistByNameUseCase(playlistRepository);
    }

    /**
     * Use Case para eliminar playlists.
     */
    @Bean
    public DeletePlaylistUseCase deletePlaylistUseCase(
        PlaylistRepository playlistRepository) {
        return new DeletePlaylistUseCase(playlistRepository);
    }

    /**
     * Bean del servicio de aplicación.
     * Orquesta los Use Cases.
     */
    @Bean
    public PlaylistApplicationService playlistApplicationService(
            CreatePlaylistUseCase createPlaylistUseCase,
            GetAllPlaylistsUseCase getAllPlaylistsUseCase,
            GetPlaylistByNameUseCase getPlaylistByNameUseCase,
            DeletePlaylistUseCase deletePlaylistUseCase) {
        return new PlaylistApplicationService(
                createPlaylistUseCase,
                getAllPlaylistsUseCase,
                getPlaylistByNameUseCase,
                deletePlaylistUseCase
        );
    }

    /**
     * Configuración del validador para Bean Validation.
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Configuración adicional de CORS a nivel de MVC.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}