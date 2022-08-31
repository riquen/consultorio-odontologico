package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dentista {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String matriculaDeCadastro;
}
