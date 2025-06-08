package com.example.api.controller;

import com.example.api.dto.AlunoResumoDTO;
import com.example.api.dto.CursoResumoDTO;
import com.example.api.dto.InscricaoRequestDTO;
import com.example.api.dto.InscricaoResponseDTO;
import com.example.api.entity.Aluno;
import com.example.api.entity.Curso;
import com.example.api.entity.Inscricao;
import com.example.api.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<?> criarInscricao(@RequestBody InscricaoRequestDTO dto) {
        try {
            Inscricao inscricao = inscricaoService.inscrever(dto.getAlunoId(), dto.getCursoId());

            Aluno aluno = inscricao.getAluno();
            Curso curso = inscricao.getCurso();

            AlunoResumoDTO alunoDTO = new AlunoResumoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
            CursoResumoDTO cursoDTO = new CursoResumoDTO(curso.getId(), curso.getNome(), curso.getDescricao());

            InscricaoResponseDTO responseDTO = new InscricaoResponseDTO(inscricao.getId(), alunoDTO, cursoDTO);

            return ResponseEntity.status(201).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao criar inscrição: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponseDTO>> listarInscricoes() {
        List<InscricaoResponseDTO> dtos = inscricaoService.listarTodas()
            .stream()
            .map(inscricao -> {
                Aluno a = inscricao.getAluno();
                Curso c = inscricao.getCurso();

                AlunoResumoDTO alunoDTO = new AlunoResumoDTO(a.getId(), a.getNome(), a.getEmail());
                CursoResumoDTO cursoDTO = new CursoResumoDTO(c.getId(), c.getNome(), c.getDescricao());

                return new InscricaoResponseDTO(inscricao.getId(), alunoDTO, cursoDTO);
            })
            .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Inscricao> inscricaoOpt = inscricaoService.buscarPorId(id);

        if (inscricaoOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Inscrição não encontrada");
        }

        Inscricao inscricao = inscricaoOpt.get();
        Aluno aluno = inscricao.getAluno();
        Curso curso = inscricao.getCurso();

        AlunoResumoDTO alunoDTO = new AlunoResumoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
        CursoResumoDTO cursoDTO = new CursoResumoDTO(curso.getId(), curso.getNome(), curso.getDescricao());

        InscricaoResponseDTO responseDTO = new InscricaoResponseDTO(inscricao.getId(), alunoDTO, cursoDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInscricao(
            @PathVariable Long id,
            @RequestBody InscricaoRequestDTO dto) {

        try {
            Inscricao inscricaoAtualizada = inscricaoService.atualizar(id, dto.getAlunoId(), dto.getCursoId());

            Aluno a = inscricaoAtualizada.getAluno();
            Curso c = inscricaoAtualizada.getCurso();

            AlunoResumoDTO alunoDTO = new AlunoResumoDTO(a.getId(), a.getNome(), a.getEmail());
            CursoResumoDTO cursoDTO = new CursoResumoDTO(c.getId(), c.getNome(), c.getDescricao());

            InscricaoResponseDTO responseDTO = new InscricaoResponseDTO(inscricaoAtualizada.getId(), alunoDTO, cursoDTO);

            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Erro ao atualizar inscrição: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarInscricao(@PathVariable Long id) {
        try {
            inscricaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
