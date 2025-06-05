package com.example.api.controller;

import com.example.api.entity.Inscricao;
import com.example.api.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<?> criarInscricao(@RequestBody Map<String, Long> body) {
        try {
            Long alunoId = body.get("alunoId");
            Long cursoId = body.get("cursoId");
            Inscricao nova = inscricaoService.inscrever(alunoId, cursoId);
            return ResponseEntity.status(201).body(nova);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao criar inscrição: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Inscricao>> listarInscricoes() {
        return ResponseEntity.ok(inscricaoService.listarTodas());
    }
}
