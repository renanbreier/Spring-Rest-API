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
}
