package com.revex.teste_tecnico.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revex.teste_tecnico.entidades.Atividade;
import com.revex.teste_tecnico.entidades.Atividade.StatusAtividade;
import com.revex.teste_tecnico.entidades.Colaborador;
import com.revex.teste_tecnico.repositorios.AtividadeRepository;
import com.revex.teste_tecnico.repositorios.ColaboradorRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Atividade salvar(Atividade atividade) {

    if (atividade.getResponsavel() != null && atividade.getResponsavel().getId() != null) {

        Long id = atividade.getResponsavel().getId();

        Colaborador colaborador = colaboradorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        atividade.setResponsavel(colaborador); // 👈 AQUI resolve tudo
    }

    return atividadeRepository.save(atividade);
}

    public List<Atividade> listarTodas() {
        return atividadeRepository.findAll();
    }

    public Atividade buscarPorId(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }

    public Atividade atualizar(Long id, StatusAtividade status, Long responsavelId) {

        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        atividade.setStatus(status);

        if (responsavelId == null) {
            atividade.setResponsavel(null);
        } else {
            Colaborador colaborador = colaboradorRepository.findById(responsavelId)
                    .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

            atividade.setResponsavel(colaborador);
        }

        return atividadeRepository.save(atividade);
    }
}