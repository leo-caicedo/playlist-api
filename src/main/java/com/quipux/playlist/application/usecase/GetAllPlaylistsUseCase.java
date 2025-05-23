package com.quipux.playlist.application.usecase;

import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.mapper.PlaylistMapper;
import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.repository.PlaylistRepository;

import java.util.List;

public class GetAllPlaylistsUseCase {
    private final PlaylistRepository playlistRepository;

    public GetAllPlaylistsUseCase(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<PlaylistResponse> execute() {
        List<Playlist> playlists = playlistRepository.findAll();
        return PlaylistMapper.toResponseList(playlists);
    }
}
