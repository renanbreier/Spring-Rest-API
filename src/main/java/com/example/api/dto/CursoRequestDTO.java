package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CursoRequestDTO {
    private String nome;
    private String descricao;

    @JsonProperty("instrutor_id")
    private Long instrutorId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Long instrutorId) {
        this.instrutorId = instrutorId;
    }
}