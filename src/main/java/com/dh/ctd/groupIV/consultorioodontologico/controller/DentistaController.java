package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.model.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.service.DentistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
   DentistaService dentistaService;

    @PostMapping
    public Dentista cadastraDentista(@RequestBody Dentista dentista) {
        return dentistaService.cadastrar(dentista);
    }

    @PatchMapping
    public Dentista alteraDentista(@RequestBody Dentista dentista) {
        return dentistaService.alterar(dentista);
    }

    @GetMapping
    public ResponseEntity consultaDentista(@RequestParam(value = "id", required = false)Long id) {
        if(id != null) {
            Optional<Dentista> optionalDentista = dentistaService.consultaDentistaPorId(id);
            if(optionalDentista.isEmpty()
            ) {
                return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(optionalDentista.get(),HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("Lista dentistas", HttpStatus.OK);
        }
    }
}

