package com.quipux.playlist.application.mapper;

import com.quipux.playlist.application.dto.CreatePlaylistRequest;
import com.quipux.playlist.application.dto.PlaylistResponse;
import com.quipux.playlist.application.dto.SongDto;
import com.quipux.playlist.domain.model.Playlist;
import com.quipux.playlist.domain.model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class PlaylistMapper {
    public static Playlist toDomain(CreatePlaylistRequest request) {
        List<Song> songs = request.getCanciones() != null ?
                request.getCanciones().stream()
                        .map(PlaylistMapper::songDtoToDomain)
                        .collect(Collectors.toList()) :
                List.of();

        Playlist playlist = new Playlist(request.getNombre(), request.getDescripcion());

        // Añadir canciones una por una para aplicar validaciones de dominio
        songs.forEach(playlist::addSong);

        return playlist;
    }

    public static PlaylistResponse toResponse(Playlist playlist) {
        List<SongDto> songDtos = playlist.getCanciones().stream()
                .map(PlaylistMapper::songDomainToDto)
                .collect(Collectors.toList());

        return new PlaylistResponse(
                playlist.getNombre(),
                playlist.getDescripcion(),
                songDtos
        );
    }

    public static Song songDtoToDomain(SongDto dto) {
        return new Song(
                dto.getTitulo(),
                dto.getArtista(),
                dto.getAlbum(),
                dto.getAnno(),
                dto.getGenero()
        );
    }

    public static SongDto songDomainToDto(Song song) {
        return new SongDto(
                song.getTitulo(),
                song.getArtista(),
                song.getAlbum(),
                song.getAnno(),
                song.getGenero()
        );
    }

    public static List<PlaylistResponse> toResponseList(List<Playlist> playlists) {
        return playlists.stream()
                .map(PlaylistMapper::toResponse)
                .collect(Collectors.toList());
    }
}
