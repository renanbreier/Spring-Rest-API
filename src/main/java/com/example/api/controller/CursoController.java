package com.example.api.controller;

import com.example.api.entity.Curso;
import com.example.api.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> criarCurso(@RequestBody Curso curso) {
        try {
            Curso novo = cursoService.salvar(curso);
            return ResponseEntity.status(201).body(novo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar curso: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

   @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(404).body("Curso não encontrado"));
}


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.atualizar(id, curso));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCurso(@PathVariable Long id) {
        try {
            cursoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
