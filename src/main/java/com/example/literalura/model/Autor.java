package com.example.literalura.model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.time.Year;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Year anoNascimento;
    private Year anoFalecimento;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;
}