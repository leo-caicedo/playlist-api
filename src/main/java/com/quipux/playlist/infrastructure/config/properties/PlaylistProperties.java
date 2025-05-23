package com.quipux.playlist.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Propiedades de configuración para el dominio de playlists.
 * Se mapean desde application.properties usando @ConfigurationProperties.
 */
@ConfigurationProperties(prefix = "app.playlist")
@Validated
public record PlaylistProperties(

        /**
         * Número máximo de canciones permitidas por playlist.
         */
        @NotNull
        @Min(1)
        @Max(1000)
        Integer maxSongsPerPlaylist,

        /**
         * Longitud máxima permitida para el nombre de una playlist.
         */
        @NotNull
        @Min(1)
        @Max(255)
        Integer maxPlaylistNameLength,

        /**
         * Longitud máxima permitida para el nombre de una canción.
         */
        @NotNull
        @Min(1)
        @Max(255)
        Integer maxSongNameLength
) {

    /**
     * Constructor con valores por defecto.
     */
    public PlaylistProperties() {
        this(100, 50, 100);
    }

    /**
     * Valida si el nombre de la playlist está dentro de los límites.
     */
    public boolean isValidPlaylistNameLength(String name) {
        return name != null &&
                name.length() > 0 &&
                name.length() <= maxPlaylistNameLength;
    }

    /**
     * Valida si el nombre de la canción está dentro de los límites.
     */
    public boolean isValidSongNameLength(String name) {
        return name != null &&
                name.length() > 0 &&
                name.length() <= maxSongNameLength;
    }

    /**
     * Valida si el número de canciones está dentro del límite.
     */
    public boolean isValidSongCount(int count) {
        return count >= 0 && count <= maxSongsPerPlaylist;
    }
}
