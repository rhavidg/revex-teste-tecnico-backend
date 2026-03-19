package com.revex.teste_tecnico.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nomeCompleto;

    @NotBlank(message = "Cargo é obrigatório")
    private String cargo;

    @NotNull(message = "Data de admissão é obrigatória")
    private LocalDate dataAdmissao;

    @NotBlank(message = "Setor é obrigatório")
    private String setor;

    @NotNull
    @Positive(message = "Salário deve ser positivo")
    private BigDecimal salario;
}
