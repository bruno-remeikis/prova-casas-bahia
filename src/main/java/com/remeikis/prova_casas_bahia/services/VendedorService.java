package com.remeikis.prova_casas_bahia.services;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.models.dto.CreateVendedorDto;
import com.remeikis.prova_casas_bahia.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor findByMatricula(String matricula) {
        return vendedorRepository
            .findById(matricula)
            .orElse(null);
    }

    public Vendedor create(CreateVendedorDto vendedorDto) {
        Vendedor vendedor = new Vendedor(
            null,
            vendedorDto.getNome(),
            vendedorDto.getDtNascimento(),
            vendedorDto.getIdentificador(),
            vendedorDto.getEmail(),
            vendedorDto.getTipoContratacao(),
            vendedorDto.getIdFilial()
        );

        return vendedorRepository.save(vendedor);
    }

    public Vendedor update(Vendedor vendedor) {
        if(!exists(vendedor)) {
            throw new IllegalArgumentException("Não existe vendedor com matrícula '" + vendedor.getMatricula() + "'");
        }
        return vendedorRepository.save(vendedor);
    }

    public Vendedor delete(String matricula) {
        Vendedor v = vendedorRepository.findById(matricula).orElse(null);
        if(v == null) {
            throw new IllegalArgumentException("Não existe vendedor com matrícula '" + matricula + "'");
        }
        vendedorRepository.delete(v);
        return v;
    }

    private boolean exists(Vendedor vendedor) {
        return vendedorRepository
            .findById(vendedor.getMatricula())
            .isPresent();
    }
}
