package com.remeikis.prova_casas_bahia.services;

import com.remeikis.prova_casas_bahia.models.MatriculaSequencer;
import com.remeikis.prova_casas_bahia.models.TipoContratacao;
import com.remeikis.prova_casas_bahia.repositories.MatriculaSequencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MatriculaSequencerService {

    private static final String FORMAT = "%d_%s";

    @Autowired
    private MatriculaSequencerRepository matriculaSequencerRepository;

    public String generateMatricula(TipoContratacao tipoContratacao) {
        return String.format(FORMAT, getNetxAndIncrement(), tipoContratacao.name());
        //return getNetxAndIncrement() + "_" + tipoContratacao.name();
        //return FORMAT.formatted()
    }

    private MatriculaSequencer getCurrent() {
        MatriculaSequencer matriculaSequencer;
        try {
            matriculaSequencer = matriculaSequencerRepository.findAll().getFirst();
        } catch (NoSuchElementException e) {
            matriculaSequencer = new MatriculaSequencer(0);
            //matriculaSequencerRepository.save(matriculaSequencer);
        }

        return matriculaSequencer;
    }

    private int getNetxAndIncrement() {
        MatriculaSequencer matriculaSequencer = getCurrent();
        matriculaSequencer.increment();
        matriculaSequencerRepository.save(matriculaSequencer);
        return matriculaSequencer.getMatricula();
    }
}
