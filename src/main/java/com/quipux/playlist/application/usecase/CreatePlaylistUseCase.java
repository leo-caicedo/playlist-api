package com.quipux.playlist.application.usecase;

import com.quipux.playlist.application.dto.CreatePlaylistRequest;
import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.mapper.PlaylistMapper;
import com.quipux.playlist.domain.exception.PlaylistNotFoundException;
import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.repository.PlaylistRepository;
import com.quipux.playlist.domain.service.PlaylistDomainService;

public class CreatePlaylistUseCase {
    private final PlaylistRepository playlistRepository;
    private final PlaylistDomainService playlistDomainService;

    public CreatePlaylistUseCase(PlaylistRepository playlistRepository, PlaylistDomainService playlistDomainService) {
        this.playlistRepository = playlistRepository;
        this.playlistDomainService = playlistDomainService;
    }

    public PlaylistResponse execute(CreatePlaylistRequest request) {
        // Validar que el nombre sea correcto
        playlistDomainService.validatePlaylistCreation(request.getNombre());

        Playlist playlist = PlaylistMapper.toDomain(request);
        Playlist savedPlaylist = playlistRepository.save(playlist);

        return PlaylistMapper.toResponse(savedPlaylist);
    }
}
