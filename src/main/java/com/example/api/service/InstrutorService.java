package com.example.api.service;

import com.example.api.entity.Instrutor;
import com.example.api.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    public Instrutor salvar(Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }

    public List<Instrutor> listarTodos() {
        return instrutorRepository.findAll();
    }

    public Optional<Instrutor> buscarPorId(Long id) {
        return instrutorRepository.findById(id);
    }

    public Instrutor atualizar(Long id, Instrutor novoInstrutor) {
        return instrutorRepository.findById(id).map(instrutor -> {
            instrutor.setEmail(novoInstrutor.getEmail());
            instrutor.setNome(novoInstrutor.getNome());
            return instrutorRepository.save(instrutor);
        }).orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));
    }

    public void deletar(Long id) {
        if (!instrutorRepository.existsById(id)) {
            throw new RuntimeException("Instrutor não encontrado");
        }
        instrutorRepository.deleteById(id);
    }
}
