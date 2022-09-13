package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.repository.EnderecoRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Paciente cadastrar(Paciente paciente) {

        return pacienteRepository.save(paciente);

    }

    public Paciente alterar(Paciente pacienteUsuario) {
        Optional <Paciente> pacienteBanco = pacienteRepository.findById(pacienteUsuario.getId());
        Paciente paciente = compararPaciente(pacienteUsuario, pacienteBanco.get());
        return pacienteRepository.save(paciente);
    }

    public void excluir(Long id) { pacienteRepository.deleteById(id); }

    public Optional<Paciente> consultaPacientePorId (Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> consultaPacientes () {

        return pacienteRepository.findAll();

    }

    private Paciente compararPaciente(Paciente pacienteUsuario, Paciente pacienteBanco) {
        String nome = (pacienteUsuario.getNome() != null) ? pacienteUsuario.getNome() : pacienteBanco.getNome();
        String sobrenome = (pacienteUsuario.getSobrenome() != null) ? pacienteUsuario.getSobrenome() : pacienteBanco.getSobrenome();
        Endereco endereco = (pacienteUsuario.getEndereco() != null) ? pacienteUsuario.getEndereco() : pacienteBanco.getEndereco();
        String rg = (pacienteUsuario.getRg() != null) ? pacienteUsuario.getRg() : pacienteBanco.getRg();
        LocalDate dataDeCadastro = (pacienteUsuario.getDataDeCadastro() != null) ? pacienteUsuario.getDataDeCadastro() : pacienteBanco.getDataDeCadastro();

        return new Paciente(pacienteUsuario.getId(), nome, sobrenome, endereco, rg, dataDeCadastro);
    }

}

