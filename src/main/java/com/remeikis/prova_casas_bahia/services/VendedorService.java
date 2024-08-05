package com.remeikis.prova_casas_bahia.services;

import com.remeikis.prova_casas_bahia.models.Filial;
import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.models.dto.CreateVendedorDto;
import com.remeikis.prova_casas_bahia.models.enums.TipoContratacao;
import com.remeikis.prova_casas_bahia.repositories.VendedorRepository;
import com.remeikis.prova_casas_bahia.utils.IdentifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private FilialService filialService;

    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    public Vendedor findByMatricula(String matricula) {
        return vendedorRepository
            .findById(matricula)
            .orElse(null);
    }

    public Vendedor create(CreateVendedorDto vendedorDto) {
        Filial filial = validateFilial(vendedorDto.getIdFilial());

        validateIdentificador(vendedorDto.getIdentificador(), vendedorDto.getTipoContratacao());

        Vendedor vendedor = new Vendedor(
            null,
            vendedorDto.getNome(),
            vendedorDto.getDtNascimento(),
            vendedorDto.getIdentificador(),
            vendedorDto.getEmail(),
            vendedorDto.getTipoContratacao(),
            vendedorDto.getIdFilial(),
            filial
        );

        return vendedorRepository.save(vendedor);
    }

    public Vendedor update(Vendedor vendedor) {
        Filial filial = validateFilial(vendedor.getIdFilial());

        validateIdentificador(vendedor.getIdentificador(), vendedor.getTipoContratacao());

        if(!exists(vendedor)) {
            throw new IllegalArgumentException("Não existe vendedor com matrícula '" + vendedor.getMatricula() + "'");
        }
        vendedor.setFilial(filial);
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

    private Filial validateFilial(int idFilial) {
        Filial filial = filialService.findById(idFilial);
        if(filial == null) {
            throw new IllegalArgumentException("Não existe filial com ID " + idFilial);
        }
        return filial;
    }

    private void validateIdentificador(String identificador, TipoContratacao tipoContratacao) throws IllegalArgumentException {
        if (tipoContratacao.equals(TipoContratacao.PJ)) {
           if (!IdentifierUtil.isCnpj(identificador))
               throw new IllegalArgumentException("O tipo de contratação " + tipoContratacao.name() + " requer um CNPJ como identificador. '" + identificador + "' não é um CNPJ válido.");
        }
        else if (!IdentifierUtil.isCpf(identificador))
            throw new IllegalArgumentException("O tipo de contratação " + tipoContratacao.name() + " requer um CPF como identificador. '" + identificador + "' não é um CPF válido.");
    }
}
