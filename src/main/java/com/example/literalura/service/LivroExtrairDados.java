package com.example.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe que extrai dados específicos de um json de livros
 * @author FelpsPrograms
 */
public class LivroExtrairDados {

    private final ObjectMapper mapper = new ObjectMapper();

    /** Método que gera o json do primeiro livro encontrado no buscarNomeLivro() */
    public String extrairPrimeiroLivro(String nomeLivro) {
        String json = new ApiCaller().buscarNomeLivro(nomeLivro);
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode resultsNode = rootNode.path("results");
        JsonNode firstElement = resultsNode.get(0);
        try {
            return mapper.writeValueAsString(firstElement);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /** Método que gera o json do autor do json do extrairPrimeiroLivro() */
    public String extrairAutor(String jsonLivro) {
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(jsonLivro);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode authorsNode = rootNode.path("authors");
        JsonNode author = authorsNode.get(0);
        try {
            return mapper.writeValueAsString(author);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
