package com.quipux.playlist.infrastructure.persistence.adapter;

import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.repository.PlaylistRepository;
import com.quipux.playlist.infrastructure.entity.PlaylistEntity;
import com.quipux.playlist.infrastructure.persistence.mapper.PlaylistEntityMapper;
import com.quipux.playlist.infrastructure.persistence.repository.JpaPlaylistRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepositoryAdapter implements PlaylistRepository {
    private final JpaPlaylistRepository jpaPlaylistRepository;

    public PlaylistRepositoryAdapter(JpaPlaylistRepository jpaPlaylistRepository) {
        this.jpaPlaylistRepository = jpaPlaylistRepository;
    }

    @Override
    @Transactional
    public Playlist save(Playlist playlist) {
        PlaylistEntity entityToSave = PlaylistEntityMapper.toEntity(playlist);
        PlaylistEntity savedEntity = jpaPlaylistRepository.save(entityToSave);

        return PlaylistEntityMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Playlist> findByNombre(String nombre) {
        return jpaPlaylistRepository.findByNombreWithSongs(nombre)
                .map(PlaylistEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> findAll() {
        List<PlaylistEntity> entities = jpaPlaylistRepository.findAll();
        return PlaylistEntityMapper.toDomainList(entities);
    }

    @Override
    @Transactional
    public void deleteByNombre(String nombre) {
        jpaPlaylistRepository.deleteByNombre((nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return jpaPlaylistRepository.existsByNombre(nombre);
    }
}
