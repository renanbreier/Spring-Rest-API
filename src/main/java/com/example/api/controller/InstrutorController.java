package com.example.api.controller;

import com.example.api.dto.InstrutorResponseDTO;
import com.example.api.entity.Instrutor;
import com.example.api.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @PostMapping
    public ResponseEntity<?> criarInstrutor(@RequestBody Instrutor instrutor) {
        try {
            Instrutor novo = instrutorService.salvar(instrutor);
            return ResponseEntity.status(201).body(novo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar instrutor: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<InstrutorResponseDTO>> listarInstrutores() {
        List<InstrutorResponseDTO> instrutores = instrutorService.listarTodos()
            .stream()
            .map(instrutor -> new InstrutorResponseDTO(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail()
            ))
            .toList();

        return ResponseEntity.ok(instrutores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Instrutor> instrutorOpt = instrutorService.buscarPorId(id);

        if (instrutorOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Instrutor não encontrado");
        }

        Instrutor instrutor = instrutorOpt.get();
        InstrutorResponseDTO dto = new InstrutorResponseDTO(
            instrutor.getId(),
            instrutor.getNome(),
            instrutor.getEmail()
        );

        return ResponseEntity.ok(dto);
    }


     @PutMapping("/{id}")
    public ResponseEntity<?> atualizarInstrutores(@PathVariable Long id, @RequestBody Instrutor instrutor) {
        try {
            return ResponseEntity.ok(instrutorService.atualizar(id, instrutor));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarInstrutores(@PathVariable Long id) {
        try {
            instrutorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}