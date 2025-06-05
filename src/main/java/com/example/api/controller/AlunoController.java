package com.example.api.controller;

import com.example.api.entity.Aluno;
import com.example.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> criarAluno(@RequestBody Aluno aluno) {
        try {
            Aluno novo = alunoService.salvar(aluno);
            return ResponseEntity.status(201).body(novo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar aluno: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }
}