package com.quipux.playlist.application.usecase;

import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.mapper.PlaylistMapper;
import com.quipux.playlist.domain.exception.PlaylistNotFoundException;
import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.repository.PlaylistRepository;

public class GetPlaylistByNameUseCase {
    private final PlaylistRepository playlistRepository;

    public GetPlaylistByNameUseCase(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public PlaylistResponse execute(String nombre) {
        Playlist playlist = playlistRepository.findByNombre(nombre)
                .orElseThrow(() -> new PlaylistNotFoundException(nombre));

        return PlaylistMapper.toResponse(playlist);
    }
}
