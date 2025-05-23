package com.quipux.playlist.infrastructure.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "playlists")
public class PlaylistEntity {
    @Id
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SongEntity> canciones = new ArrayList<>();

    public PlaylistEntity() {}

    public PlaylistEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void addSong(SongEntity song) {
        canciones.add(song);
        song.setPlaylist(this);
    }

    public void removeSong(SongEntity song) {
        canciones.remove(song);
        song.setPlaylist(null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SongEntity> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<SongEntity> canciones) {
        this.canciones = canciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistEntity that = (PlaylistEntity) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
