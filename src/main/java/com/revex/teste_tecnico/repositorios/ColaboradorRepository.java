package com.revex.teste_tecnico.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revex.teste_tecnico.entidades.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
}