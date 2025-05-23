package com.quipux.playlist.domain.exception;

public class InvalidPlaylistNameException extends RuntimeException {
    public InvalidPlaylistNameException(String message) {
        super(message);
    }
}
