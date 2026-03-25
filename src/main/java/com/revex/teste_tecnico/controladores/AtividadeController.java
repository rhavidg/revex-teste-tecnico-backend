package com.revex.teste_tecnico.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revex.teste_tecnico.entidades.Atividade;
import com.revex.teste_tecnico.servicos.AtividadeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @PostMapping
    public ResponseEntity<Atividade> criar(
            @RequestBody @Valid Atividade atividade,
            @RequestParam(required = false) Long responsavelId) {
        Atividade salva = service.salvar(atividade);
        return ResponseEntity.ok(salva);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Long id) {
        Atividade atividade = service.buscarPorId(id);
        return ResponseEntity.ok(atividade);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Atividade> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {

        String status = (String) body.get("status");

        Long responsavelId = null;

        if (body.get("responsavel") != null) {
            Map<String, Object> responsavel = (Map<String, Object>) body.get("responsavel");
            responsavelId = Long.valueOf(responsavel.get("id").toString());
        }

        Atividade.StatusAtividade statusEnum = Atividade.StatusAtividade.valueOf(status);

        Atividade atualizada = service.atualizar(id, statusEnum, responsavelId);
        return ResponseEntity.ok(atualizada);
    }
}