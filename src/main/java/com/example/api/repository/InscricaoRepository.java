package com.example.api.repository;

import com.example.api.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    Optional<Inscricao> findByAlunoIdAndCursoId(Long alunoId, Long cursoId);

}
