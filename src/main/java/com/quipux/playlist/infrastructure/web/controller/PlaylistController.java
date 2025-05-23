package com.quipux.playlist.infrastructure.web.controller;

import com.quipux.playlist.application.dto.CreatePlaylistRequest;
import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.service.PlaylistApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
@CrossOrigin(origins = "*")
public class PlaylistController {
    private final PlaylistApplicationService playlistApplicationService;

    public PlaylistController(PlaylistApplicationService playlistApplicationService) {
        this.playlistApplicationService = playlistApplicationService;
    }

    @PostMapping()
    public ResponseEntity<PlaylistResponse> createPlaylist(@Valid @RequestBody CreatePlaylistRequest request) {
        PlaylistResponse createdPlaylist = playlistApplicationService.createPlaylist(request);

        // Crear URI para el recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{listName}")
                .buildAndExpand(createdPlaylist.getNombre())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(createdPlaylist);
    }

    @GetMapping()
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists() {
        List<PlaylistResponse> playlists = playlistApplicationService.getAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<PlaylistResponse> getPlaylistByName(@PathVariable String listName) {
        PlaylistResponse playlist = playlistApplicationService.getPlaylistByName(listName);
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String listName) {
        playlistApplicationService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }
}
