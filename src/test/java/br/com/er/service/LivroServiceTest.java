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
import static org.mockito.Mockito.lenient;

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
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private PanacheQuery<Livro> queryMock;

    @Test
    void shouldReturnTrueWhenANewLivroIsCreated() {
        Livro expected = TestUtils.createLivroEntity();

        when(livroRepository.save(any(Livro.class))).thenReturn(expected);

        Livro livro = livroService.salvar(expected);

        assertNotNull(livro);
        assertEquals(expected, livro);
    }

    @Test
    void shouldReturnTrueWhenAListOfLivroIsSearched() {
        List<Livro> expected = List.of(TestUtils.createLivroEntity());

        when(queryMock.list()).thenReturn(expected);
        when(livroRepository.findAll()).thenReturn(queryMock);

        List<Livro> livros = livroService.buscarTodos();

        assertNotNull(livros);
        assertEquals(expected, livros);
    }

    @Test
    void shouldReturnTrueWhenALivroIsSearched() {
        Livro expected = TestUtils.createLivroEntity();

        when(livroRepository.findById(anyLong())).thenReturn(expected);

        Livro livro = livroService.buscar(1L);

        assertNotNull(livro);
        assertEquals(expected, livro);
    }

    @Test
    void shouldReturnTrueWhenALivroIsEdited() {
        Livro original = TestUtils.createLivroEntity();
        Livro updatedLivro = TestUtils.createLivroEntity();
        updatedLivro.setAutor("New author");

        lenient().when(livroRepository.findById(anyLong())).thenReturn(original);
        when(livroRepository.editar(anyLong(), any(Livro.class))).thenReturn(updatedLivro);

        Livro newLivro = livroService.editar(1L, updatedLivro);

        assertNotNull(newLivro);
        assertEquals(updatedLivro, newLivro);
    }

    @Test
    void shouldReturnTrueWhenALivroIsDeleted() {
        doNothing().when(livroRepository).excluir(anyLong());
        when(livroRepository.findById(anyLong())).thenThrow(NaoEncontradoException.class);

        livroService.excluir(1L);

        assertThrows(NaoEncontradoException.class, () -> livroService.buscar(1L));
    }

    @Test
    void shouldSaveBook() {
        Livro livro = TestUtils.createLivroEntity();

        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro resultado = livroService.salvar(livro);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Titulo", resultado.getTitulo());
    }

    @Test
    void shouldFindAllBooks() {
        Livro livro1 = TestUtils.createLivroEntity();
        livro1.setTitulo("Titulo Teste 1");

        Livro livro2 = TestUtils.createLivroEntity();
        livro2.setId(2L);
        livro2.setTitulo("Titulo Teste 2");

        when(queryMock.list()).thenReturn(List.of(livro1, livro2));
        when(livroRepository.findAll()).thenReturn(queryMock);

        List<Livro> resultado = livroService.buscarTodos();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Titulo Teste 1", resultado.get(0).getTitulo());
        assertEquals("Titulo Teste 2", resultado.get(1).getTitulo());
    }

    @Test
    void shouldFindBookById() {
        Livro livro = TestUtils.createLivroEntity();

        when(livroRepository.findById(anyLong())).thenReturn(livro);

        Livro resultado = livroService.buscar(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Titulo", resultado.getTitulo());
    }

    @Test
    void shouldEditBook() {
        Livro livroExistente = TestUtils.createLivroEntity();
        Livro livroNovo = TestUtils.createLivroEntity();
        livroNovo.setTitulo("Titulo Novo");

        when(livroRepository.editar(anyLong(), any(Livro.class))).thenReturn(livroNovo);

        Livro resultado = livroService.editar(1L, livroNovo);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Titulo Novo", resultado.getTitulo());
    }

    @Test
    void shouldDeleteBook() {
        doNothing().when(livroRepository).excluir(anyLong());

        assertDoesNotThrow(() -> livroService.excluir(1L));
    }
}
