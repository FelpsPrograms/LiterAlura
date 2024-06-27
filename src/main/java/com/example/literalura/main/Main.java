package com.example.literalura.main;

import com.example.literalura.model.Autor;
import com.example.literalura.model.DadosAutor;
import com.example.literalura.model.DadosLivro;
import com.example.literalura.model.Livro;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.service.ConverteDados;
import com.example.literalura.service.LivroExtrairDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner teclado = new Scanner(System.in);
    private final LivroExtrairDados extrator = new LivroExtrairDados();
    private final ConverteDados conversor = new ConverteDados();
    private final AutorRepository repository;

    public Main(AutorRepository livroRepository) {
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
        String jsonLivro = extrator.extrairPrimeiroLivro(titulo);
        DadosLivro dadosLivro = conversor.obterDados(jsonLivro, DadosLivro.class);
        Livro livro = new Livro(dadosLivro);
        List<Livro> livros = new ArrayList<>();
        livros.add(livro);
        String jsonAutor = extrator.extrairAutor(jsonLivro);
        DadosAutor dadosAutor = conversor.obterDados(jsonAutor, DadosAutor.class);
        Autor autor = new Autor(dadosAutor);
        autor.setLivros(livros);
        repository.save(autor);
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
