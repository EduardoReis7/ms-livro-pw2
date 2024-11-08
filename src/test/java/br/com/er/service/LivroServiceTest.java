package br.com.er.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.er.exception.NaoEncontradoException;
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

    @Test
    void shouldReturnTrueWhenALivroIsSearched() {

        Livro expected = TestUtils.createLivroEntity();

        when(repository.findById(anyLong())).thenReturn(expected);

        Livro livro = service.buscar(1L);

        assertNotNull(livro);
        assertEquals(expected, livro);
    }

    @Test
    void shouldReturnTrueWhenALivroIsEdited() {

        Livro original = TestUtils.createLivroEntity();
        Livro updatedLivro = TestUtils.createLivroEntity();

        updatedLivro.setAutor("New author");

        when(repository.findById(anyLong())).thenReturn(original);

        Livro newLivro = service.editar(1L, updatedLivro);

        assertNotNull(newLivro);
        assertEquals(updatedLivro, newLivro);
    }

    @Test
    void shouldReturnTrueWhenALivroIsDeleted() {

        doNothing().when(repository).excluir(anyLong());

        service.excluir(1L);

        assertThrows(NaoEncontradoException.class, () -> this.service.buscar(1L));
    }
}
