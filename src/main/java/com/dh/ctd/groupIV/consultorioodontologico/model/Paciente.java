package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Paciente {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    private LocalDate dataDeCadastro;
}
