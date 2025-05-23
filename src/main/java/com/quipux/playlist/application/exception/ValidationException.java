package com.quipux.playlist.application.exception;

import java.util.List;

public class ValidationException extends ApplicationException {
    private final List<String> errors;

    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationException(List<String> errors) {
        super("Errores de validacion encontrada");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
