package com.dh.ctd.groupIV.consultorioodontologico.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dentista {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String matriculaDeCadastro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dentista dentista = (Dentista) o;
        return Objects.equals(nome, dentista.nome) && Objects.equals(sobrenome, dentista.sobrenome) && Objects.equals(matriculaDeCadastro, dentista.matriculaDeCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome, matriculaDeCadastro);
    }
}
