package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(unique = true)
    private String rg;

    private LocalDate dataDeCadastro;
}
