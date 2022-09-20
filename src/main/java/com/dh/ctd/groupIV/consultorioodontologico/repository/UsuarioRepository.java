package com.dh.ctd.groupIV.consultorioodontologico.repository;


import com.dh.ctd.groupIV.consultorioodontologico.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}