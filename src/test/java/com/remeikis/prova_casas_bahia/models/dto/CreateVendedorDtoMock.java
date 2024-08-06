package com.remeikis.prova_casas_bahia.models.dto;

import com.remeikis.prova_casas_bahia.models.Vendedor;
import com.remeikis.prova_casas_bahia.models.enums.TipoContratacao;

import java.util.Date;

public abstract class CreateVendedorDtoMock {
    private CreateVendedorDtoMock() {}

    public static CreateVendedorDto single() {
        return new CreateVendedorDto(
            "Teste",
            new Date(),
            "871.104.950-27",
            "teste@email.com",
            TipoContratacao.CLT,
            1
        );
    }
}
