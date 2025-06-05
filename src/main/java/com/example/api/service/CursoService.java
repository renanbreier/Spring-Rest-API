package com.example.api.service;

import com.example.api.entity.Curso;
import com.example.api.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso atualizar(Long id, Curso novoCurso) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(novoCurso.getNome());
            curso.setDescricao(novoCurso.getDescricao());
            curso.setInstrutor(novoCurso.getInstrutor());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public void deletar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
