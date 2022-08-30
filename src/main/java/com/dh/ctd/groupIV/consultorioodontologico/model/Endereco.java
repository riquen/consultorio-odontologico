package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Endereco {
    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private Estado estado;
    private String cep;
}
