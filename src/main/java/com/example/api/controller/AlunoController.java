package com.example.api.controller;

import com.example.api.dto.AlunoResponseDTO;
import com.example.api.dto.CursoResumoDTO;
import com.example.api.entity.Aluno;
import com.example.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos() {
        List<AlunoResponseDTO> alunos = alunoService.listarTodos()
            .stream()
            .map(a -> new AlunoResponseDTO(a.getId(), a.getNome(), a.getEmail()))
            .toList();

        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> alunoOpt = alunoService.buscarPorId(id);

        if (alunoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Aluno não encontrado");
        }

        Aluno a = alunoOpt.get();
        AlunoResponseDTO dto = new AlunoResponseDTO(a.getId(), a.getNome(), a.getEmail());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<?> listarCursosDoAluno(@PathVariable Long id) {
        Optional<Aluno> alunoOpt = alunoService.buscarPorId(id);

        if (alunoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Aluno não encontrado");
        }

        Aluno aluno = alunoOpt.get();

        List<CursoResumoDTO> cursos = aluno.getCursos()
            .stream()
            .map(curso -> new CursoResumoDTO(curso.getId(), curso.getNome(), curso.getDescricao()))
            .toList();

        return ResponseEntity.ok(cursos);
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