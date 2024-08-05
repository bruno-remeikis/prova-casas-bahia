package com.remeikis.prova_casas_bahia.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.remeikis.prova_casas_bahia.models.enums.TipoContratacao;
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
public class CreateVendedorDto {

    private String nome;

    @Nullable
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtNascimento;

    private String identificador; // CPF ou CNPJ

    private String email;

    private TipoContratacao tipoContratacao;

    private int idFilial;

}
