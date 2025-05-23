package com.quipux.playlist.application.service;

import com.quipux.playlist.application.dto.CreatePlaylistRequest;
import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.usecase.CreatePlaylistUseCase;
import com.quipux.playlist.application.usecase.DeletePlaylistUseCase;
import com.quipux.playlist.application.usecase.GetAllPlaylistsUseCase;
import com.quipux.playlist.application.usecase.GetPlaylistByNameUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PlaylistApplicationService {
    private final CreatePlaylistUseCase createPlaylistUseCase;
    private final GetAllPlaylistsUseCase getAllPlaylistsUseCase;
    private final GetPlaylistByNameUseCase getPlaylistByNameUseCase;
    private final DeletePlaylistUseCase deletePlaylistUseCase;

    public PlaylistApplicationService(CreatePlaylistUseCase createPlaylistUseCase,
                                      GetAllPlaylistsUseCase getAllPlaylistsUseCase,
                                      GetPlaylistByNameUseCase getPlaylistByNameUseCase,
                                      DeletePlaylistUseCase deletePlaylistUseCase) {
        this.createPlaylistUseCase = createPlaylistUseCase;
        this.getAllPlaylistsUseCase = getAllPlaylistsUseCase;
        this.getPlaylistByNameUseCase = getPlaylistByNameUseCase;
        this.deletePlaylistUseCase = deletePlaylistUseCase;
    }

    @Transactional
    public PlaylistResponse createPlaylist(CreatePlaylistRequest request) {
        return createPlaylistUseCase.execute(request);
    }

    @Transactional(readOnly = true)
    public List<PlaylistResponse> getAllPlaylists() {
        return getAllPlaylistsUseCase.execute();
    }

    @Transactional(readOnly = true)
    public PlaylistResponse getPlaylistByName(String nombre) {
        return getPlaylistByNameUseCase.execute(nombre);
    }

    @Transactional
    public void deletePlaylist(String nombre) {
        deletePlaylistUseCase.execute(nombre);
    }
}
