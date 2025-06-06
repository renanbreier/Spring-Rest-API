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

        // Adiciona o curso ao aluno e o aluno ao curso
        aluno.getCursos().add(curso);
        curso.getAlunos().add(aluno);

        // Salva novamente ambos (opcional, dependendo do CascadeType)
        alunoRepository.save(aluno);
        cursoRepository.save(curso);

        return inscricaoRepository.save(new Inscricao(aluno, curso));
    }

    public List<Inscricao> listarTodas() {
        return inscricaoRepository.findAll();
    }

     public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }
}