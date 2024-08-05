package com.remeikis.prova_casas_bahia.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filial {

    private int id;

    private String nome;

    private String cnpj;

    private String cidade;

    private String uf;

    private String tipo;

    private boolean ativo;

    private Date dtCadastro;

    private Date dtAtualizacao;

}
