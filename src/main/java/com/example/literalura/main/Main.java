package com.example.literalura.main;

import com.example.literalura.model.*;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.repository.LivroRepository;
import com.example.literalura.service.ConverteDados;
import com.example.literalura.service.LivroExtrairDados;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final Scanner teclado = new Scanner(System.in);
    private final LivroExtrairDados extrator = new LivroExtrairDados();
    private final ConverteDados conversor = new ConverteDados();
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public Main(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
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
        Optional<DadosLivro> optionalDadosLivro = Optional.ofNullable(conversor.obterDados(jsonLivro, DadosLivro.class));
        if (optionalDadosLivro.isPresent()) {
            DadosLivro dadosLivro = optionalDadosLivro.get();
            Optional<Livro> optionalLivro = livroRepository.findByTitulo(dadosLivro.titulo());
            Livro livro;
            if (optionalLivro.isEmpty()) {
                livro = new Livro(dadosLivro);
                String jsonAutor = extrator.extrairAutor(jsonLivro);
                DadosAutor dadosAutor = conversor.obterDados(jsonAutor, DadosAutor.class);
                Optional<Autor> optionalAutor = autorRepository.findByNome(dadosAutor.nome());
                List<Livro> livros;
                if (optionalAutor.isEmpty()) {
                    livros = new ArrayList<>();
                    livros.add(livro);
                    Autor autor = new Autor(dadosAutor);
                    autor.setLivros(livros);
                    autorRepository.save(autor);
                } else {
                    Autor foundAtor = optionalAutor.get();
                    livros = foundAtor.getLivros();
                    livros.add(livro);
                    foundAtor.setLivros(livros);
                    autorRepository.save(foundAtor);
                }
            } else {
                livro = optionalLivro.get();
            }
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }

    }

    private void listarLivros() {
        List<Livro> livrosRegistrados = livroRepository.findAll();
        livrosRegistrados.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autoresRegistrados = autorRepository.findAll();
        autoresRegistrados.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        int anoEscolhido;
        System.out.println("Digite o ano:");
        try {
            anoEscolhido = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Digite somente números!");
        }
            List<Autor> autoresVivos = autorRepository.listarAutoresVivos(anoEscolhido);
            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor vivo nesse ano :(");
            } else {
                autoresVivos.forEach(System.out::println);
            }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Escolha um dos idiomas abaixo:
                
                pt - português
                en - inglês
                es - espanhol
                """);
        String idiomaSelecionado = teclado.nextLine();
        List<Livro> livrosNoIdioma = Collections.emptyList();
        try {
            livrosNoIdioma = livroRepository.findAll().stream()
                    .filter(l -> l.getIdioma().equals(Idioma.fromString(idiomaSelecionado)))
                    .toList();
        } catch (IllegalArgumentException e) {
            System.out.println("Opção inválida!");
        }
        if (livrosNoIdioma.isEmpty()) {
            System.out.println("Nenhum livro cadastrado nesse idioma!");
        } else {
            livrosNoIdioma.forEach(System.out::println);
        }
    }

}
