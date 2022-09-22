package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.service.DentistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public Dentista cadastraDentista(@RequestBody Dentista dentista) throws CadastroInvalidoException {

        return dentistaService.cadastrar(dentista);

    }

    @PatchMapping
    public Dentista alteraDentista(@RequestBody Dentista dentista) throws ResourceNotFoundException {

        return dentistaService.alterar(dentista);

    }

    @GetMapping
    public ResponseEntity consultaDentista(@RequestParam(value = "id", required = false) Long id) throws ResourceNotFoundException {
        if (id != null) {
            Dentista dentista = dentistaService.consultaDentistaPorId(id);
            return new ResponseEntity(dentista, HttpStatus.OK);
        } else {
            return new ResponseEntity(dentistaService.consultaDentistas(), HttpStatus.OK);
        }
    }
}

