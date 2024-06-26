package com.example.literalura.repository;

import com.example.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

}
