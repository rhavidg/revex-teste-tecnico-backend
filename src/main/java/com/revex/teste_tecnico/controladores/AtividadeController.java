package com.revex.teste_tecnico.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revex.teste_tecnico.entidades.Atividade;
import com.revex.teste_tecnico.entidades.Atividade.StatusAtividade;
import com.revex.teste_tecnico.servicos.AtividadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @PostMapping
    public ResponseEntity<Atividade> criar(
            @RequestBody @Valid Atividade atividade,
            @RequestParam(required = false) Long responsavelId) {
        Atividade salva = service.salvar(atividade, responsavelId);
        return ResponseEntity.ok(salva);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Atividade> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");

        Atividade.StatusAtividade statusEnum = Atividade.StatusAtividade.valueOf(status);

        Atividade atualizada = service.atualizarStatus(id, statusEnum);
        return ResponseEntity.ok(atualizada);
    }

    @PatchMapping("/{id}/responsavel")
    public ResponseEntity<Atividade> atualizarResponsavel(
            @PathVariable Long id,
            @RequestBody Map<String, Long> body) {
        Long responsavelId = body.get("responsavelId");

        Atividade atualizada = service.atualizarResponsavel(id, responsavelId);
        return ResponseEntity.ok(atualizada);
    }
}