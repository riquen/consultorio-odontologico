package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.service.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping
    public ResponseEntity cadastraConsulta(@RequestBody Consulta consulta) throws CadastroInvalidoException {
        return new ResponseEntity(consultaService.cadastrar(consulta), HttpStatus.OK);
    }

    @PatchMapping
    public Consulta alteraConsulta(@RequestBody Consulta consulta) throws ResourceNotFoundException, CadastroInvalidoException {
        return consultaService.alterar(consulta);
    }

    @GetMapping
    public ResponseEntity consultaConsulta(@RequestParam(value = "id", required = false)Long id) throws ResourceNotFoundException {
        if(id != null) {
            Consulta consulta = consultaService.consultaConsultaPorId(id);
            return new ResponseEntity(consulta,HttpStatus.OK);
        } else {
            return new ResponseEntity(consultaService.consultaConsultas(), HttpStatus.OK);
        }
    }
}

