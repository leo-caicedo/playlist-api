package com.quipux.playlist.application.dto;

import java.util.List;

public class PlaylistResponse {
    private String nombre;
    private String descripcion;
    private List<SongDto> canciones;
    private int totalCanciones;

    public PlaylistResponse(String nombre, String descripcion, List<SongDto> canciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
        this.totalCanciones = canciones != null ? canciones.size() : 0;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<SongDto> getCanciones() { return canciones; }
    public void setCanciones(List<SongDto> canciones) {
        this.canciones = canciones;
        this.totalCanciones = canciones != null ? canciones.size() : 0;
    }

    public int getTotalCanciones() { return totalCanciones; }
    public void setTotalCanciones(int totalCanciones) { this.totalCanciones = totalCanciones; }
}
