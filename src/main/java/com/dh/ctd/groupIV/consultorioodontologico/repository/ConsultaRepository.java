package com.dh.ctd.groupIV.consultorioodontologico.repository;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByDentistaAndDataHora(Dentista dentista, LocalDateTime data);
}
