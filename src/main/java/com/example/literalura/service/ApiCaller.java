package com.example.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe que traz dados da API
 * @author FelpsPrograms
 */
public class ApiCaller {

    /** Método que busca as informações de todos os livros que tenham o nomeLivro em seu título */
    public String buscarNomeLivro(String nomeLivro) {
        String path = "https://gutendex.com/books/?search=";
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(path + nomeLivro.replace(" ", "+")))
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
