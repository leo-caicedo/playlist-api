package com.quipux.playlist.domain.model;

import java.util.Objects;

public final class Song {
    private final String titulo;
    private final String artista;
    private final String album;
    private final String anno;
    private final String genero;

    public Song(String titulo, String artista, String album, String anno, String genero) {
        validateBasicData(titulo, artista);

        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.album = album != null ? album.trim() : "";
        this.anno = anno != null ? anno.trim() : "";
        this.genero = genero != null ? genero.trim() : "";
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getAnno() {
        return anno;
    }

    public String getGenero() {
        return genero;
    }

    private void validateBasicData(String titulo, String artista) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título de la canción no puede estar vacío");
        }
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("El artista no puede estar vacío");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(titulo, song.titulo) &&
                Objects.equals(artista, song.artista) &&
                Objects.equals(album, song.album) &&
                Objects.equals(anno, song.anno) &&
                Objects.equals(genero, song.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista, album, anno, genero);
    }

    @Override
    public String toString() {
        return String.format("Song{titulo='%s', artista='%s', album='%s', anno='%s', genero='%s'}",
                titulo, artista, album, anno, genero);
    }
}
