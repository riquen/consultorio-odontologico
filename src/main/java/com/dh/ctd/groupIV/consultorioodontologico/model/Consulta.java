package com.dh.ctd.groupIV.consultorioodontologico.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //private Paciente paciente;
    //private Dentista dentista;
    private LocalDateTime dataHora;
}
