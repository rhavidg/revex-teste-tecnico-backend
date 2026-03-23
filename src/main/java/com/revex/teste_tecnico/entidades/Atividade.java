package com.revex.teste_tecnico.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atividade {
    public enum StatusAtividade {
        PENDENTE,
        EM_ANDAMENTO,
        FINALIZADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAtividade status = StatusAtividade.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = true)
    private Colaborador responsavel;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = StatusAtividade.PENDENTE;
        }
    }

    public void setStatus(StatusAtividade status) {
        this.status = (status == null) ? StatusAtividade.PENDENTE : status;
    }
}