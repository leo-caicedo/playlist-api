package com.quipux.playlist.domain.exception;

public class PlaylistAlreadyExistsException extends RuntimeException {
    private final String playlistName;

    public PlaylistAlreadyExistsException(String playlistName) {
        super(String.format("Ya existe una playlist con el nombre '%s'", playlistName));
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }
}
