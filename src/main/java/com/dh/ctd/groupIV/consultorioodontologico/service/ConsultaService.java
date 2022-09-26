package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import com.dh.ctd.groupIV.consultorioodontologico.repository.ConsultaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.DentistaRepository;
import com.dh.ctd.groupIV.consultorioodontologico.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    DentistaRepository dentistaRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    Logger logger = Logger.getLogger(ConsultaService.class);

    public Consulta cadastrar(Consulta consulta) throws CadastroInvalidoException {

        this.validaConsulta(consulta);

        Consulta consultaSalva = consultaRepository.save(consulta);
        logger.info("Consulta cadastrada com sucesso!");
        return consultaRepository.findById(consultaSalva.getId()).get();
    }

    public Consulta alterar(Consulta consultaUsuario) throws ResourceNotFoundException, CadastroInvalidoException {
        Consulta consultaBanco = consultaRepository.findById(consultaUsuario.getId())
                .orElseThrow(() ->{
                    logger.error("Consulta não encontrada");
                    return new ResourceNotFoundException("Requisição inválida");
                });
        Consulta consulta = compararConsulta(consultaUsuario, consultaBanco);
        this.validaConsulta(consulta);
        Consulta consultaSalva = consultaRepository.save(consulta);
        logger.info("Consulta alterada com sucesso!");
        return consultaSalva;
    }

    public Consulta consultaConsultaPorId(Long id) throws ResourceNotFoundException {

        return consultaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Consulta não encontrada");
                    return new ResourceNotFoundException("Requisição inválida");
                });

    }

    public List<Consulta> consultaConsultas() {

        return consultaRepository.findAll();

    }

    private Consulta compararConsulta(Consulta consultaUsuario, Consulta consultaBanco) {
        Paciente paciente = (consultaUsuario.getPaciente() != null) ? consultaUsuario.getPaciente() : consultaBanco.getPaciente();
        Dentista dentista = (consultaUsuario.getDentista() != null) ? consultaUsuario.getDentista() : consultaBanco.getDentista();
        LocalDateTime dataHora = (consultaUsuario.getDataHora() != null) ? consultaUsuario.getDataHora() : consultaBanco.getDataHora();
        return new Consulta(consultaUsuario.getId(), paciente, dentista, dataHora);
    }

    private void validaConsulta(Consulta consulta) throws CadastroInvalidoException {
        dentistaRepository.findById(consulta.getDentista().getId()).
                orElseThrow(() -> {
                    logger.error("ID de Dentista inválido!");
                    return new CadastroInvalidoException("Requisição inválida");
                });

        pacienteRepository.findById(consulta.getPaciente().getId()).
                orElseThrow(() -> {
                    logger.error("ID de Paciente inválido!");
                    return new CadastroInvalidoException("Requisição inválida");
                });

        if (!consultaRepository.findByDentistaAndDataHora(consulta.getDentista(), consulta.getDataHora()).isEmpty()) {
            logger.error("Dentista já possui uma consulta nesse horário!");
            throw new CadastroInvalidoException("Dentista já possui uma consulta nesse horário");
        }

    }
}

