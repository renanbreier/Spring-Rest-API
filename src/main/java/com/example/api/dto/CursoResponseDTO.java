package com.example.api.dto;

public class CursoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private InstrutorResponseDTO instrutor;

    public CursoResponseDTO(Long id, String nome, String descricao, InstrutorResponseDTO instrutor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.instrutor = instrutor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public InstrutorResponseDTO getInstrutor() {
        return instrutor;
    }
}
