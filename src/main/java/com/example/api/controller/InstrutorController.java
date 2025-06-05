package com.example.api.controller;

import com.example.api.entity.Instrutor;
import com.example.api.service.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Instrutor>> listarInstrutores() {
        return ResponseEntity.ok(instrutorService.listarTodos());
    }
}