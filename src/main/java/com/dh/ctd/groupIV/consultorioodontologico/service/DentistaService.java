package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.model.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    DentistaRepository dentistaRepository;

    public Dentista cadastrar(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public Dentista alterar(Dentista dentistaUsuario) {
        Optional<Dentista> dentistaBanco = dentistaRepository.findById(dentistaUsuario.getId());
        Dentista dentista = compararDentista(dentistaUsuario, dentistaBanco.get());
        return dentistaRepository.save(dentista);
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

