package com.remeikis.prova_casas_bahia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendedor_sequence")
    //@GenericGenerator()
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dtNascimento;

    @Column(nullable = false)
    private String identificador; // CPF ou CNPJ

    @Column(nullable = false)
    private String email;

    //@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoContratacao tipoContratacao;

    @Column(nullable = false)
    private int idFilial;
}
