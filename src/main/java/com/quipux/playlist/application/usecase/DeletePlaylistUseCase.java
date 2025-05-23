package com.quipux.playlist.application.usecase;

import com.quipux.playlist.domain.exception.PlaylistNotFoundException;
import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.repository.PlaylistRepository;

public class DeletePlaylistUseCase {
    private final PlaylistRepository playlistRepository;

    public DeletePlaylistUseCase(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void execute(String nombre) {
        // 1. Verificar que existe la playlist
        Playlist playlist = playlistRepository.findByNombre(nombre)
                .orElseThrow(() -> new PlaylistNotFoundException(nombre));

        playlistRepository.deleteByNombre(nombre);
    }
}
