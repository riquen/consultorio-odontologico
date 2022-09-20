package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository dentistaRepository;

    Logger logger = Logger.getLogger(DentistaService.class);

    public Dentista cadastrar(Dentista dentista) {
        Dentista dentistaSalvo = dentistaRepository.save(dentista);
        logger.info("Dentista salvo com sucesso!");
        return dentistaSalvo;
    }

    public Dentista alterar(Dentista dentistaUsuario) {
        Optional<Dentista> dentistaBanco = dentistaRepository.findById(dentistaUsuario.getId());
        Dentista dentista = compararDentista(dentistaUsuario, dentistaBanco.get());
        Dentista dentistaSalvo = dentistaRepository.save(dentista);
        logger.info("Dentista alterado com sucesso!");
        return dentistaSalvo;
    }

    public Optional<Dentista> consultaDentistaPorId (Long id) {
        return dentistaRepository.findById(id);
    }

    public List<Dentista> consultaDentistas () {

        return dentistaRepository.findAll();

    }

    private Dentista compararDentista(Dentista dentistaUsuario, Dentista dentistaBanco) {
        String nome = (dentistaUsuario.getNome() != null) ? dentistaUsuario.getNome() : dentistaBanco.getNome();
        String sobrenome = (dentistaUsuario.getSobrenome() != null) ? dentistaUsuario.getSobrenome() : dentistaBanco.getSobrenome();
        String matriculaDeCadastro = (dentistaUsuario.getMatriculaDeCadastro() != null) ? dentistaUsuario.getMatriculaDeCadastro() : dentistaBanco.getMatriculaDeCadastro();

        return new Dentista(dentistaUsuario.getId(), nome, sobrenome, matriculaDeCadastro);
    }

}

