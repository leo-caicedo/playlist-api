package com.quipux.playlist.application.dto;

import jakarta.validation.constraints.NotBlank;

public class SongDto {
    @NotBlank(message = "El título de la canción es obligatorio")
    private String titulo;

    @NotBlank(message = "El artista es obligatorio")
    private String artista;

    private String album;
    private String anno;
    private String genero;

    public SongDto() {}

    public SongDto(String titulo, String artista, String album, String anno, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anno = anno;
        this.genero = genero;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public String getAnno() { return anno; }
    public void setAnno(String anno) { this.anno = anno; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
