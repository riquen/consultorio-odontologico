package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Consulta {
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime dataHora;
}
