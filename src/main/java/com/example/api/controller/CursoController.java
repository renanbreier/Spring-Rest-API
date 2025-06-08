package com.example.api.controller;

import com.example.api.dto.AlunoResumoDTO;
import com.example.api.dto.CursoRequestDTO;
import com.example.api.dto.CursoResponseDTO;
import com.example.api.dto.CursoResumoDTO;
import com.example.api.dto.InstrutorResponseDTO;
import com.example.api.entity.Aluno;
import com.example.api.entity.Curso;
import com.example.api.entity.Instrutor;
import com.example.api.service.CursoService;
import com.example.api.service.InstrutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private InstrutorService instrutorService;

    @PostMapping
    public ResponseEntity<?> criarCurso(@RequestBody CursoRequestDTO dto) {
        try {
            Optional<Instrutor> instrutorOpt = instrutorService.buscarPorId(dto.getInstrutorId());
            if (instrutorOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Instrutor não encontrado");
            }

            Curso curso = new Curso();
            curso.setNome(dto.getNome());
            curso.setDescricao(dto.getDescricao());
            curso.setInstrutor(instrutorOpt.get());

            Curso novo = cursoService.salvar(curso);

            // Monta o DTO de resposta
            Instrutor instrutor = novo.getInstrutor();
            InstrutorResponseDTO instrutorDTO = new InstrutorResponseDTO(
                instrutor.getId(), instrutor.getNome(), instrutor.getEmail()
            );

            CursoResponseDTO responseDTO = new CursoResponseDTO(
                novo.getId(), novo.getNome(), novo.getDescricao(), instrutorDTO
            );

            return ResponseEntity.status(201).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar curso: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        List<CursoResponseDTO> cursos = cursoService.listarTodos()
            .stream()
            .map(curso -> {
                Instrutor instrutor = curso.getInstrutor();
                InstrutorResponseDTO instrutorDTO = new InstrutorResponseDTO(
                    instrutor.getId(),
                    instrutor.getNome(),
                    instrutor.getEmail()
                );
                return new CursoResponseDTO(
                    curso.getId(),
                    curso.getNome(),
                    curso.getDescricao(),
                    instrutorDTO
                );
            })
            .toList();

        return ResponseEntity.ok(cursos);
    }

   @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Curso> cursoOpt = cursoService.buscarPorId(id);

        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Curso não encontrado");
        }

        Curso curso = cursoOpt.get();
        Instrutor instrutor = curso.getInstrutor();

        InstrutorResponseDTO instrutorDTO = new InstrutorResponseDTO(
            instrutor.getId(),
            instrutor.getNome(),
            instrutor.getEmail()
        );

        CursoResponseDTO cursoDTO = new CursoResponseDTO(
            curso.getId(),
            curso.getNome(),
            curso.getDescricao(),
            instrutorDTO
        );

        return ResponseEntity.ok(cursoDTO);
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<?> listarAlunosDoCurso(@PathVariable Long id) {
        Optional<Curso> cursoOpt = cursoService.buscarPorId(id);

        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Curso não encontrado");
        }

        Curso curso = cursoOpt.get();

        List<AlunoResumoDTO> alunos = curso.getAlunos()
            .stream()
            .map(aluno -> new AlunoResumoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail()))
            .toList();

        return ResponseEntity.ok(alunos);
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
