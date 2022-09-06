package com.dh.ctd.groupIV.consultorioodontologico.repository;

import com.dh.ctd.groupIV.consultorioodontologico.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
