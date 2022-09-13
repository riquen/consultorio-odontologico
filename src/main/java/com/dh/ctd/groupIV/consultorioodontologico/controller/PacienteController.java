package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public Paciente cadastraPaciente(@RequestBody Paciente paciente) {

        return pacienteService.cadastrar(paciente);

    }

    @PatchMapping
    public Paciente alteraPaciente(@RequestBody Paciente paciente) {
        return pacienteService.alterar(paciente);
    }

    @GetMapping
    public ResponseEntity consultaPaciente(@RequestParam(value = "id", required = false)Long id) {
        if(id != null) {
            Optional<Paciente> optionalPaciente = pacienteService.consultaPacientePorId(id);
            if(optionalPaciente.isEmpty()
            ) {
                return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(optionalPaciente.get(),HttpStatus.OK);
            }
        } else {
            return new ResponseEntity(pacienteService.consultaPacientes(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) {
        pacienteService.excluir(id);}
}

