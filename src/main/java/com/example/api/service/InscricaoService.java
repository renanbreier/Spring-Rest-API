package com.example.api.service;

import com.example.api.entity.Aluno;
import com.example.api.entity.Curso;
import com.example.api.entity.Inscricao;
import com.example.api.repository.AlunoRepository;
import com.example.api.repository.CursoRepository;
import com.example.api.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Inscricao inscrever(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Verifica se já existe inscrição com esse aluno + curso (opcional, boa prática)
        Optional<Inscricao> existente = inscricaoRepository
            .findByAlunoIdAndCursoId(alunoId, cursoId);

        if (existente.isPresent()) {
            throw new RuntimeException("Inscrição já existe para este aluno e curso");
        }

        // Apenas cria e salva a inscrição diretamente
        return inscricaoRepository.save(new Inscricao(aluno, curso));
    }

    public List<Inscricao> listarTodas() {
        return inscricaoRepository.findAll();
    }

    public Optional<Inscricao> buscarPorId(Long id) {
        return inscricaoRepository.findById(id);
    }

    public Inscricao atualizar(Long id, Long novoAlunoId, Long novoCursoId) {
        Inscricao inscricao = inscricaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));

        Aluno aluno = alunoRepository.findById(novoAlunoId)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Curso curso = cursoRepository.findById(novoCursoId)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        inscricao.setAluno(aluno);
        inscricao.setCurso(curso);

        return inscricaoRepository.save(inscricao);
    }

    public void deletar(Long id) {
        if (!inscricaoRepository.existsById(id)) {
            throw new RuntimeException("Inscrição não encontrado");
        }
        inscricaoRepository.deleteById(id);
    }

}