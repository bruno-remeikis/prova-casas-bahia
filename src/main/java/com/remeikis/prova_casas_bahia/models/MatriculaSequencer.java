package com.remeikis.prova_casas_bahia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaSequencer {

    @Id
    private int matricula;

    public void increment() {
        matricula += 1;
    }

}
