package br.com.er.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroInDto {

    private Long id;
    private String titulo;
    private String autor;
    private LocalDate dataLancamento;
    
}
