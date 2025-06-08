package com.example.api.dto;

public class InscricaoResponseDTO {
    private Long id;
    private AlunoResumoDTO aluno;
    private CursoResumoDTO curso;

    public InscricaoResponseDTO(Long id, AlunoResumoDTO aluno, CursoResumoDTO curso) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public AlunoResumoDTO getAluno() {
        return aluno;
    }

    public CursoResumoDTO getCurso() {
        return curso;
    }
}
