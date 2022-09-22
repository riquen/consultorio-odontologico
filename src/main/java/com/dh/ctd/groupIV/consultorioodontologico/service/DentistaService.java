package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository dentistaRepository;

    Logger logger = Logger.getLogger(DentistaService.class);

    public Dentista cadastrar(Dentista dentista) throws CadastroInvalidoException {
        Dentista dentistaCadastrado = null;
        try {
            dentistaCadastrado = dentistaRepository.save(dentista);
        } catch (Exception e) {
            logger.error("Erro ao cadastrar dentista");
            logger.error(e.getMessage());
            throw new CadastroInvalidoException("Cadastro inválido");
        }
        logger.info("Dentista cadastrado com sucesso!");
        return dentistaCadastrado;
    }

    public Dentista alterar(Dentista dentistaUsuario) throws ResourceNotFoundException {
        Dentista dentistaBanco = dentistaRepository.findById(dentistaUsuario.getId())
                .orElseThrow(() -> {
                    logger.error("Dentista não encontrado");
                    return new ResourceNotFoundException("Requisição inválida");
                });
        Dentista dentista = compararDentista(dentistaUsuario, dentistaBanco);
        Dentista dentistaSalvo = dentistaRepository.save(dentista);
        logger.info("Dentista alterado com sucesso!");
        return dentistaSalvo;
    }

    public Dentista consultaDentistaPorId(Long id) throws ResourceNotFoundException {

        return dentistaRepository.findById(id).orElseThrow(() -> {
            logger.error("Dentista não encontrado");
            return new ResourceNotFoundException("Requisição inválida");
        });

    }

    public List<Dentista> consultaDentistas() {

        return dentistaRepository.findAll();

    }

    private Dentista compararDentista(Dentista dentistaUsuario, Dentista dentistaBanco) {
        String nome = (dentistaUsuario.getNome() != null) ? dentistaUsuario.getNome() : dentistaBanco.getNome();
        String sobrenome = (dentistaUsuario.getSobrenome() != null) ? dentistaUsuario.getSobrenome() : dentistaBanco.getSobrenome();
        String matriculaDeCadastro = (dentistaUsuario.getMatriculaDeCadastro() != null) ? dentistaUsuario.getMatriculaDeCadastro() : dentistaBanco.getMatriculaDeCadastro();

        return new Dentista(dentistaUsuario.getId(), nome, sobrenome, matriculaDeCadastro);
    }

}

