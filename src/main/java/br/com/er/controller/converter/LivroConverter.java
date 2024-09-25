package br.com.er.controller.converter;

import br.com.er.model.Livro;
import br.com.er.model.dto.LivroInDto;
import br.com.er.model.dto.LivroOutDto;
import java.util.List;

public class LivroConverter {
    
    public static LivroOutDto convertEntityToOutDto(Livro livro) {
        return LivroOutDto.builder()
                        .titulo(livro.getTitulo())
                        .autor(livro.getAutor())
                        .dataLancamento(livro.getDataLancamento())
                        .build();
    }

    public static Livro convertInDtoToEntity(LivroInDto dto) {
        return Livro.builder()
                        .id(dto.getId())
                        .titulo(dto.getTitulo())
                        .autor(dto.getAutor())
                        .dataLancamento(dto.getDataLancamento())
                        .build();
    }

    public static List<LivroOutDto> convertEntityListToOutDtoList(List<Livro> entityList) {
        return entityList.stream().map(e -> convertEntityToOutDto(e)).toList();
    }
}
