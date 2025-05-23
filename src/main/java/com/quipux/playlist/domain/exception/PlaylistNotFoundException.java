package com.quipux.playlist.domain.exception;

public class PlaylistNotFoundException extends RuntimeException {
    private final String playlistName;

    public PlaylistNotFoundException(String playlistName) {
        super(String.format("Playlist con nombre '%s' no fue encontrada", playlistName));
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }
}
