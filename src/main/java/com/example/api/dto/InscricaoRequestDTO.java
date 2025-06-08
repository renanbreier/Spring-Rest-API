package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InscricaoRequestDTO {

    @JsonProperty("aluno_id")
    private Long alunoId;

    @JsonProperty("curso_id")
    private Long cursoId;

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
