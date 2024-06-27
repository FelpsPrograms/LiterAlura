package com.example.literalura.teste;

import com.example.literalura.model.DadosLivro;
import com.example.literalura.service.ApiCaller;
import com.example.literalura.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Scanner;

public class Teste {

    public static void main(String[] args) throws JsonProcessingException {
        ApiCaller apiCaller = new ApiCaller();
        Scanner teclado = new Scanner(System.in);
        ConverteDados conversor = new ConverteDados();

        System.out.println("Digite um título para ser buscado:");
        String titulo = teclado.nextLine();
        String json = apiCaller.obterDadosLivro(titulo);

        // Criar um ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(json);
        JsonNode resultsNode = rootNode.path("results");
        JsonNode firstElement = resultsNode.get(0);
        String jsonNovo = mapper.writeValueAsString(firstElement);
        System.out.println(jsonNovo);

        System.out.println(conversor.obterDados(jsonNovo, DadosLivro.class));
    }

}
