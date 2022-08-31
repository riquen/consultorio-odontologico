package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @GetMapping(value = "")
    public String cadastraEndereco() throws SQLException {

        Endereco endereco = new Endereco(null, "Oi", "Teste", "Teste", "Teste", "Teste", "Teste", "Teste", "Teste");
        service.cadastrar(endereco);

        return "Hello Endereco";

    }
}