package com.remeikis.prova_casas_bahia.models;

import com.remeikis.prova_casas_bahia.models.enums.TipoContratacao;

import java.util.Date;

public abstract class VendedorMock {
    private VendedorMock() {}

    public static Vendedor single() {
        return new Vendedor(
            null,
            "Teste",
            new Date(),
            "871.104.950-27",
            "teste@email.com",
            TipoContratacao.CLT,
            1,
            null
        );
    }
}
