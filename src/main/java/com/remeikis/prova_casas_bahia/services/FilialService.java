package com.remeikis.prova_casas_bahia.services;

import com.remeikis.prova_casas_bahia.models.Filial;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FilialService
{
    private static final List<Filial> FILIAIS = List.of(
        new Filial(1, "Filial 1", "11.111.111/0001-00", "Vitória", "ES", "Tipo 1", true, new Date(), null),
        new Filial(2, "Filial 2", "22.222.222/0001-00", "Vitória", "ES", "Tipo 2", true, new Date(), null),
        new Filial(3, "Filial 3", "33.333.333/0001-00", "São Paulo", "SP", "Tipo 1", true, new Date(), null),
        new Filial(4, "Filial 4", "44.444.444/0001-00", "Rio de Janeiro", "RJ", "Tipo 2", true, new Date(), null)
    );

    public List<Filial> findAll() {
        return FILIAIS;
    };

    public Filial findById(int id) {
        return FILIAIS.stream()
            .filter(f -> f.getId() == id)
            .findFirst()
            .orElse(null);
    }
}
