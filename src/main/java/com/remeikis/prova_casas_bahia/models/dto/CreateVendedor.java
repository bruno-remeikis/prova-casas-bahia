package com.remeikis.prova_casas_bahia.models.dto;

import com.remeikis.prova_casas_bahia.models.TipoContratacao;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVendedor {

    private String matricula;

    private String nome;

    @Nullable
    private Date dtNascimento;

    private String identificador; // CPF ou CNPJ

    private String email;

    private TipoContratacao tipoContratacao;

    private int idFilial;

}
