package com.remeikis.prova_casas_bahia.repositories;

import com.remeikis.prova_casas_bahia.models.MatriculaSequencer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaSequencerRepository extends JpaRepository<MatriculaSequencer, Integer> {
}
