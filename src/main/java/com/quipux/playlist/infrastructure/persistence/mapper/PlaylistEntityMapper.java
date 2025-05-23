package com.quipux.playlist.infrastructure.persistence.mapper;

import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.model.Song;
import com.quipux.playlist.infrastructure.entity.PlaylistEntity;
import com.quipux.playlist.infrastructure.entity.SongEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaylistEntityMapper {
    public static Playlist toDomain(PlaylistEntity entity) {
        if (entity == null) return null;

        List<Song> songs = entity.getCanciones().stream()
                .map(PlaylistEntityMapper::songEntityToDomain)
                .collect(Collectors.toList());

        return new Playlist(
                entity.getNombre(),
                entity.getDescripcion(),
                songs
        );
    }

    public static PlaylistEntity toEntity(Playlist domain) {
        if (domain == null) return null;

        PlaylistEntity entity = new PlaylistEntity(
                domain.getNombre(),
                domain.getDescripcion()
        );

        List<SongEntity> songEntities = domain.getCanciones().stream()
                .map(song -> songDomainToEntity(song, entity))
                .collect(Collectors.toList());

        entity.setCanciones((List<SongEntity>) songEntities);

        return entity;
    }

    public static Song songEntityToDomain(SongEntity entity) {
        if (entity == null) return null;

        return new Song(
                entity.getTitulo(),
                entity.getArtista(),
                entity.getAlbum(),
                entity.getAnno(),
                entity.getGenero()
        );
    }

    public static SongEntity songDomainToEntity(Song domain, PlaylistEntity playlist) {
        if (domain == null) return null;

        SongEntity entity = new SongEntity(
                domain.getTitulo(),
                domain.getArtista(),
                domain.getAlbum(),
                domain.getAnno(),
                domain.getGenero()
        );

        entity.setPlaylist(playlist);
        return entity;
    }

    public static List<Playlist> toDomainList(List<PlaylistEntity> entities) {
        return entities.stream()
                .map(PlaylistEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
