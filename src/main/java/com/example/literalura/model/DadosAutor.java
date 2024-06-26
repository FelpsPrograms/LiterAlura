package com.example.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Year;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") int anoNascimento,
        @JsonAlias("death_year") int anoFalecimento
) {
}
