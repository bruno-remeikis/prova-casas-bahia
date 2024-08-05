package com.remeikis.prova_casas_bahia.services;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private MatriculaSequencerService matriculaSequencerService;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor findByMatricula(String matricula) {
        return vendedorRepository
            .findById(matricula)
            .orElse(null);
    }

    public Vendedor create(Vendedor vendedor) {
        if(exists(vendedor)) {
            throw new IllegalArgumentException("Já existe um vendedor com matrícula " + vendedor.getMatricula());
        }
        String matricula = matriculaSequencerService.generateMatricula(vendedor.getTipoContratacao());
        vendedor.setMatricula(matricula);
        return vendedorRepository.save(vendedor);
    }

    public Vendedor update(Vendedor vendedor) {
        if(!exists(vendedor)) {
            throw new IllegalArgumentException("Já existe um vendedor com matrícula " + vendedor.getMatricula());
        }
        return vendedorRepository.save(vendedor);
    }

    private boolean exists(Vendedor vendedor) {
        return vendedorRepository
            .findById(vendedor.getMatricula())
            .isPresent();
    }
}
