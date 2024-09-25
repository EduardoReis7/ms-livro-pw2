package br.com.er.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    @Temporal(TemporalType.DATE)
    private LocalDate dataLancamento;
    
    public Livro() {
    }

    public Livro(Long id, String titulo, String autor, LocalDate dataLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.dataLancamento = dataLancamento;
    }
}
