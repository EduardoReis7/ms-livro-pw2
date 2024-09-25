package br.com.er.service;

import br.com.er.model.Livro;
import br.com.er.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LivroService {
    
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return this.livroRepository.save(livro);
    }

    public List<Livro> buscarTodos() {
        return this.livroRepository.findAll().list();
    }

    public Livro buscar(Long id) {
        return this.livroRepository.findById(id);
    }

    public Livro editar(Long id, Livro novo) {
        return this.livroRepository.editar(id, novo);
    }

    public void excluir(Long id) {
        this.livroRepository.excluir(id);
    }
}
