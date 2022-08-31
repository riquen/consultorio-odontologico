package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endereco {
    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;
    private String pais;
}
