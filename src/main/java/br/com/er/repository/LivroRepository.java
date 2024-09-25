package br.com.er.repository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import br.com.er.exception.NaoEncontradoException;
import br.com.er.model.Livro;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro> {
    
    public Livro findById(Long id) {
        return findByIdOptional(id).orElseThrow(() -> new NaoEncontradoException("Não foi possível localizar o recurso solicitado."));
    }

    public Livro save(Livro livro) {
        persist(livro);
        return livro;
    }

    public PanacheQuery findAll() {
        return findAll(Sort.ascending("titulo"));
    }

    public Livro editar(Long id, Livro novo) {
        Livro livroDb = findById(id);
        
        livroDb.setTitulo(novo.getTitulo());
        livroDb.setAutor(novo.getAutor());
        livroDb.setDataLancamento(novo.getDataLancamento());

        return livroDb;
    }

    public void excluir(Long id) {
        Livro livroDb = this.findById(id);
        if (livroDb != null) {
            deleteById(id);
        }
    }
}
