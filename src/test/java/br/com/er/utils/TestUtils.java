package br.com.er.utils;

import java.time.LocalDate;

import br.com.er.model.Livro;
import br.com.er.model.dto.LivroInDto;
import br.com.er.model.dto.LivroOutDto;

public class TestUtils {
    
    public static Livro createLivroEntity() {
        return Livro.builder()
            .id(1L)
            .autor("Autor")
            .titulo("Titulo")
            .dataLancamento(LocalDate.now())
            .build();
    }

    public static LivroOutDto createLivroOutDto() {
        return LivroOutDto.builder()
            .autor("Autor")
            .titulo("Titulo")
            .dataLancamento(LocalDate.now())
            .build();
    }

    public static LivroInDto createLivroInDto() {
        return LivroInDto.builder()
            .id(1L)
            .autor("Autor")
            .titulo("Titulo")
            .dataLancamento(LocalDate.now())
            .build();
    }
}
