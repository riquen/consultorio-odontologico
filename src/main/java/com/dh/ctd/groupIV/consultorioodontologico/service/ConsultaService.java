package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.repository.ConsultaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    DentistaRepository dentistaRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    Logger logger = Logger.getLogger(ConsultaService.class);

    public Consulta cadastrar(Consulta consulta) throws SQLException {
      if (dentistaRepository.findById(consulta.getDentista().getId()).isEmpty()){
          logger.error("ID de Dentista inválido!");
          throw new SQLException();
      };
      Consulta consultaSalva = consultaRepository.saveAndFlush(consulta);
      logger.info("Consulta cadastrada com sucesso!");
      return consultaRepository.findById(consultaSalva.getId()).get();
    }

    public Consulta alterar(Consulta consultaUsuario) {
        Optional <Consulta> consultaBanco = consultaRepository.findById(consultaUsuario.getId());
        Consulta consulta = compararConsulta(consultaUsuario, consultaBanco.get());
        Consulta consultaSalva = consultaRepository.save(consulta);
        logger.info("Consulta alterada com sucesso!");
        return consultaSalva;
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

