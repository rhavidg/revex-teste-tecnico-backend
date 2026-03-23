package com.revex.teste_tecnico.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revex.teste_tecnico.entidades.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}