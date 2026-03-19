package com.revex.teste_tecnico.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revex.teste_tecnico.entidades.Colaborador;
import com.revex.teste_tecnico.repositorios.ColaboradorRepository;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Colaborador salvar(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public List<Colaborador> listarTodos() {
        return repository.findAll();
    }
}
