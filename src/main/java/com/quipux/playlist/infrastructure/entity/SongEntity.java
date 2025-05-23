package com.quipux.playlist.infrastructure.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "songs")
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "artista", nullable = false)
    private String artista;

    @Column(name = "album")
    private String album;

    @Column(name = "anno")
    private String anno;

    @Column(name = "genero")
    private String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_nombre", referencedColumnName = "nombre")
    private PlaylistEntity playlist;

    public SongEntity() {}

    public SongEntity(String titulo, String artista, String album, String anno, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anno = anno;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public PlaylistEntity getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistEntity playlists) {
        this.playlist = playlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongEntity that = (SongEntity) o;
        return Objects.equals(titulo, that.titulo) &&
                Objects.equals(artista, that.artista) &&
                Objects.equals(album, that.album) &&
                Objects.equals(anno, that.anno) &&
                Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista, album, anno, genero);
    }
}
