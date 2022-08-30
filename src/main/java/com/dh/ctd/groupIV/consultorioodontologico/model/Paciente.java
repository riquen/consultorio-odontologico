package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Paciente {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    private LocalDate dataDeCadastro;
}
