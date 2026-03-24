package com.revex.teste_tecnico.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revex.teste_tecnico.entidades.Colaborador;
import com.revex.teste_tecnico.servicos.ColaboradorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @PostMapping
    public ResponseEntity<Colaborador> criar(@RequestBody @Valid Colaborador colaborador) {
        Colaborador salvo = service.salvar(colaborador);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}