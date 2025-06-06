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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(404).body("Aluno não encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAlunos(@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            return ResponseEntity.ok(alunoService.atualizar(id, aluno));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAlunos(@PathVariable Long id) {
        try {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}