package com.dh.ctd.groupIV.consultorioodontologico.repository;

import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
