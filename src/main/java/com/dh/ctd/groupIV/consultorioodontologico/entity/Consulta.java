package com.dh.ctd.groupIV.consultorioodontologico.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "id_dentista")
    private Dentista dentista;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dataHora;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(paciente.getId(), consulta.paciente.getId()) && Objects.equals(dentista.getId(), consulta.dentista.getId()) && Objects.equals(dataHora, consulta.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paciente.getId(), dentista.getId(), dataHora);
    }
}
