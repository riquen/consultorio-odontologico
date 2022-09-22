package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    Logger logger = Logger.getLogger(PacienteService.class);

    public Paciente cadastrar(Paciente paciente) throws CadastroInvalidoException {
        Paciente pacienteCadastrado = null;
        try {
            pacienteCadastrado = pacienteRepository.save(paciente);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar paciente");
            logger.error(e.getMessage());
            throw new CadastroInvalidoException("Cadastro inválido");
        }
        logger.info("Paciente cadastrado com sucesso!");
        return pacienteCadastrado;
    }

    public Paciente alterar(Paciente pacienteUsuario) throws ResourceNotFoundException {
        Paciente pacienteBanco = pacienteRepository.findById(pacienteUsuario.getId())
                .orElseThrow(() ->{
                    logger.error("Paciente não encontrado");
                    return new ResourceNotFoundException("Requisição inválida");
                });
        Paciente paciente = compararPaciente(pacienteUsuario, pacienteBanco);
        Paciente pacienteAlterado = pacienteRepository.save(paciente);
        logger.info("Paciente alterado com sucesso!");
        return pacienteAlterado;
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        pacienteRepository.findById(id).orElseThrow(() ->{
            logger.error("Paciente não encontrado");
            return new ResourceNotFoundException("Requisição inválida");
        });
        pacienteRepository.deleteById(id);
        logger.info("Paciente excluído com sucesso!");
    }

    public Paciente consultaPacientePorId (Long id) throws ResourceNotFoundException {

        return pacienteRepository.findById(id).orElseThrow(() ->{
            logger.error("Paciente não encontrado");
            return new ResourceNotFoundException("Requisição inválida");
        });

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

