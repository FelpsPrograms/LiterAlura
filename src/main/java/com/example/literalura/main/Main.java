package com.example.literalura.main;

import com.example.literalura.model.DadosLivro;
import com.example.literalura.model.Livro;
import com.example.literalura.repository.LivroRepository;
import com.example.literalura.service.ApiCaller;
import com.example.literalura.service.ConverteDados;

import java.util.Scanner;

public class Main {

    private final Scanner teclado = new Scanner(System.in);
    private final ApiCaller consultor = new ApiCaller();
    private final ConverteDados conversor = new ConverteDados();
    private final LivroRepository repository;

    public Main(LivroRepository livroRepository) {
        this.repository = livroRepository;
    }

    public void showSystem() {
        System.out.println("*** LiterAlura ***");
        String menu =
                """   
                
                Escolha uma das opções:
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """
                ;
        int opcaoSelecionada;
        do {
            System.out.println(menu);
            opcaoSelecionada = teclado.nextInt();
            teclado.nextLine();
            switch (opcaoSelecionada) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcaoSelecionada != 0);
    }

    private void buscarLivroPorTitulo() {
        System.out.println("Digite um título para ser buscado:");
        String titulo = teclado.nextLine();
        String json = consultor.obterDadosLivro(titulo);
        DadosLivro dadosLivro = conversor.obterDados(json, DadosLivro.class);
        Livro livro = new Livro(dadosLivro);
        repository.save(livro);
        System.out.println(dadosLivro);
    }

    private void listarLivros() {
    }

    private void listarAutores() {
    }

    private void listarAutoresVivos() {
    }

    private void listarLivrosPorIdioma() {
    }

}
