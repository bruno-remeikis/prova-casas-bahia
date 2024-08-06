package com.remeikis.prova_casas_bahia.models;

import java.util.Date;

public abstract class FilialMock {
    private FilialMock() {}

    public static Filial single() {
        return new Filial(1, "Filial 1", "11.111.111/0001-00", "Vitória", "ES", "Tipo 1", true, new Date(), null);
    }
}
