package com.quipux.playlist.domain.service;

import com.quipux.playlist.domain.exception.PlaylistAlreadyExistsException;
import com.quipux.playlist.domain.exception.PlaylistNotFoundException;
import com.quipux.playlist.domain.repository.PlaylistRepository;

public class PlaylistDomainService {
    private final PlaylistRepository playlistRepository;

    public PlaylistDomainService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void validatePlaylistCreation(String nombre) {
        if (playlistRepository.existsByNombre(nombre)) {
            throw new PlaylistAlreadyExistsException(nombre);
        }
    }

    public void validatePlaylistExists(String nombre) {
        if (!playlistRepository.existsByNombre(nombre)) {
            throw  new PlaylistNotFoundException(nombre);
        }
    }
}
