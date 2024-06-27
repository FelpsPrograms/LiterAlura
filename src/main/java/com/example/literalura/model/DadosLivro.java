package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("") String titulo,
        @JsonAlias("") Idioma idioma,
        @JsonAlias("") int numeroDownloads
) {
}
