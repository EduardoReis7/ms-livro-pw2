package br.com.er.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroOutDto {
    
    private String titulo;
    private String autor;
    private LocalDate dataLancamento;
}
