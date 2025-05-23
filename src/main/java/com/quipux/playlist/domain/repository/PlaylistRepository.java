package com.quipux.playlist.domain.repository;

import com.quipux.playlist.domain.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository {
    Playlist save(Playlist playlist);
    Optional<Playlist> findByNombre(String nombre);
    List<Playlist> findAll();
    void deleteByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
