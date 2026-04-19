package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Acervo {
    private final LivrosRepository livroRepository;
    private final AutorRepository  autorRepository;

    @Autowired
    public Acervo(LivrosRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @PostConstruct
    public void init() {
    }

    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    public List<String> getTitulos() {
        return getAll()
                .stream()
                .map(livro -> livro.getTitulo())
                .toList();
    }

    public List<Autor> getAutores() {
        return autorRepository.findAll();
    }

    public List<Livro> getLivrosDoAutor(Autor autor) {
        return livroRepository.findByAutor(autor);
    }

    public Livro getLivroTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public boolean cadastraLivroNovo(Livro livro) {
        livroRepository.save(livro);
        return true;
    }

    public boolean removeLivro(long codigo) {
        if (livroRepository.existsById(codigo)) {
            livroRepository.deleteById(codigo);
            return true;
        }
        return false;
    }

    public List<Livro> getLivrosDoAutorEAno(String nomeAutor, int ano) {
        return livroRepository.findByAutorNomeAndAno(nomeAutor, ano);
    }

    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }
    public Autor getAutorByNome(String nome) {
        return autorRepository.findByNome(nome).orElse(null);
    }
}
