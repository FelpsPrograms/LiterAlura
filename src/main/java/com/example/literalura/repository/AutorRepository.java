package com.example.literalura.repository;

import com.example.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a " +
            "FROM Autor a " +
            "WHERE a.anoNascimento < :anoVerificacao AND a.anoFalecimento > :anoVerificacao")
    List<Autor> listarAutoresVivos(int anoVerificacao);

}
