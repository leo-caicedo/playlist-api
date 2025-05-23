package com.quipux.playlist.infrastructure.persistence.repository;

import com.quipux.playlist.infrastructure.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPlaylistRepository extends JpaRepository<PlaylistEntity, String> {
    Optional<PlaylistEntity> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    void deleteByNombre(String nombre);

    @Query("SELECT p FROM PlaylistEntity p LEFT JOIN FETCH p.canciones WHERE p.nombre = :nombre")
    Optional<PlaylistEntity> findByNombreWithSongs(@Param("nombre") String nombre);

    @Query("SELECT COUNT(s) FROM PlaylistEntity p JOIN p.canciones s WHERE p.nombre = :nombre")
    Long countSongsByPlaylistName(@Param("nombre") String nombre);
}
