package com.quipux.playlist.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CreatePlaylistRequest {
    @NotBlank(message = "El nombre de la playlist es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    private String descripcion;

    @Valid
    private List<SongDto> canciones;

    public CreatePlaylistRequest() {}

    public CreatePlaylistRequest(String nombre, String descripcion, List<SongDto> canciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<SongDto> getCanciones() { return canciones; }
    public void setCanciones(List<SongDto> canciones) { this.canciones = canciones; }
}
