package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.model.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.model.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.repository.ConsultaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;



    public Consulta cadastrar(Consulta consulta) {
      Consulta consultaSalva = consultaRepository.saveAndFlush(consulta);
      return consultaRepository.findById(consultaSalva.getId()).get();
    }

    public Consulta alterar(Consulta consultaUsuario) {
        Optional <Consulta> consultaBanco = consultaRepository.findById(consultaUsuario.getId());
        Consulta consulta = compararConsulta(consultaUsuario, consultaBanco.get());
        return consultaRepository.save(consulta);
    }

    private Consulta compararConsulta(Consulta consultaUsuario, Consulta consultaBanco) {
        Paciente paciente = (consultaUsuario.getPaciente() != null) ? consultaUsuario.getPaciente() : consultaBanco.getPaciente();
        Dentista dentista = (consultaUsuario.getDentista() != null) ? consultaUsuario.getDentista() : consultaBanco.getDentista();
        LocalDateTime dataHora = (consultaUsuario.getDataHora() != null) ? consultaUsuario.getDataHora() : consultaBanco.getDataHora();

        return new Consulta(consultaUsuario.getId(), paciente, dentista, dataHora);
    }

    public Optional<Consulta> consultaConsultaPorId (Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> consultaConsultas () {

        return consultaRepository.findAll();

    }

}

