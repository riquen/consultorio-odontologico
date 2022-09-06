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
    public String cadastraEndereco() {

        Endereco endereco = new Endereco(null, "Oi", "Teste", "Teste", "Teste", "Teste", "Teste", "Teste", "Teste");
        service.cadastrar(endereco);

        return "Hello Endereco";

    }

    @GetMapping(value = "/alterar")
    public String alteraEndereco() {

        Endereco endereco = new Endereco(new Long(2), "Rua Legal", "123", null, null, null, null, null, null);
        service.alterar(endereco);

        return "Hello alteração";
    }

    @GetMapping(value = "/excluir")
    public String excluiEndereco() {

        Endereco endereco = new Endereco(new Long(2), "Rua Legal", "123", null, null, null, null, null, null);
        service.excluir(endereco.getId());

        return "Hello exclusão";
    }
}