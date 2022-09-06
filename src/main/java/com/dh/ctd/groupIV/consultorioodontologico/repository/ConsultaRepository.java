package com.dh.ctd.groupIV.consultorioodontologico.repository;

import com.dh.ctd.groupIV.consultorioodontologico.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
