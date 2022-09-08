package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public Paciente cadastrar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente alterar(Paciente pacienteUsuario) {
        Paciente pacienteBanco = pacienteRepository.getReferenceById(pacienteUsuario.getId());
        Paciente paciente = compararPaciente(pacienteUsuario, pacienteBanco);
        return pacienteRepository.save(paciente);
    }

    private Paciente compararPaciente(Paciente pacienteUsuario, Paciente pacienteBanco) {
        String nome = (pacienteUsuario.getNome() != null) ? pacienteUsuario.getNome() : pacienteBanco.getNome();
        String sobrenome = (pacienteUsuario.getSobrenome() != null) ? pacienteUsuario.getSobrenome() : pacienteBanco.getSobrenome();
        Endereco endereco = (pacienteUsuario.getEndereco() != null) ? pacienteUsuario.getEndereco() : pacienteBanco.getEndereco();
        String rg = (pacienteUsuario.getRg() != null) ? pacienteUsuario.getRg() : pacienteBanco.getRg();
        LocalDate dataDeCadastro = (pacienteUsuario.getDataDeCadastro() != null) ? pacienteUsuario.getDataDeCadastro() : pacienteBanco.getDataDeCadastro();

        return new Paciente(pacienteUsuario.getId(), nome, sobrenome, endereco, rg, dataDeCadastro);
    }

    public Optional<Paciente> consultaPacientePorId (Long id) {
        return pacienteRepository.findById(id);
    }
}

