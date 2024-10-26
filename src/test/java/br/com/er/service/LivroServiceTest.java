package br.com.er.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.er.model.Livro;
import br.com.er.repository.LivroRepository;
import br.com.er.utils.TestUtils;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @InjectMocks
    LivroService service;

    @Mock
    LivroRepository repository;
    
    @Test
    void shouldReturnTrueWhenANewLivroIsCreated() {

        Livro expected = TestUtils.createLivroEntity();

        when(repository.save(any(Livro.class))).thenReturn(expected);

        Livro livro = service.salvar(expected);

        assertNotNull(livro);
        assertEquals(expected, livro);
    } 

    @Test
    void shouldReturnTrueWhenAListOfLivroIsSearched() {

        List<Livro> expected = List.of(TestUtils.createLivroEntity());

        PanacheQuery<Livro> mockQuery = mock(PanacheQuery.class);
        when(mockQuery.list()).thenReturn(expected);
        when(repository.findAll()).thenReturn(mockQuery);

        List<Livro> livros = service.buscarTodos();

        assertNotNull(livros);
        assertEquals(expected, livros);
    }
}
