package com.quipux.playlist.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private String nombre;
    private String descripcion;
    private final List<Song> canciones;

    public Playlist(String nombre, String descripcion) {
        validateNombre(nombre);
        this.nombre = nombre.trim();
        this.descripcion = descripcion != null ? descripcion.trim() : "";
        this.canciones = new ArrayList<>();
    }

    public Playlist(String nombre, String descripcion, List<Song> canciones) {
        validateNombre(nombre);
        this.nombre = nombre.trim();
        this.descripcion = descripcion != null ? descripcion.trim() : "";
        this.canciones = new ArrayList<>(canciones != null ? canciones : new ArrayList<>());
    }

    private void validateNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la playlist no puede estar vacío");
        }
        if (nombre.trim().length() > 100) {
            throw new IllegalArgumentException("El nombre de la playlist no puede exceder 100 caracteres");
        }
    }

    public void addSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("La canción no puede ser null");
        }

        // Regla de negocio: no permitir canciones duplicadas
        if (canciones.contains(song)) {
            throw new IllegalArgumentException("La canción ya existe en la playlist");
        }

        canciones.add(song);
    }

    public void removeSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("La canción no puede ser null");
        }

        if (!canciones.remove(song)) {
            throw new IllegalArgumentException("La canción no existe en la playlist");
        }
    }

    public void updateDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion != null ? nuevaDescripcion.trim() : "";
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public List<Song> getCanciones() { return Collections.unmodifiableList(canciones); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(nombre, playlist.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return String.format("Playlist{nombre='%s', descripcion='%s', totalCanciones=%d}",
                nombre, descripcion, canciones.size());
    }
}
