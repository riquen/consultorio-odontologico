package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public Paciente cadastraPaciente(@RequestBody Paciente paciente) throws CadastroInvalidoException {

        return pacienteService.cadastrar(paciente);

    }

    @PatchMapping
    public Paciente alteraPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        return pacienteService.alterar(paciente);
    }

    @GetMapping
    public ResponseEntity consultaPaciente(@RequestParam(value = "id", required = false)Long id) throws ResourceNotFoundException {
        if(id != null) {
            Paciente paciente = pacienteService.consultaPacientePorId(id);
            return new ResponseEntity(paciente,HttpStatus.OK);
        } else {
            return new ResponseEntity(pacienteService.consultaPacientes(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) throws ResourceNotFoundException {
        pacienteService.excluir(id);}
}

