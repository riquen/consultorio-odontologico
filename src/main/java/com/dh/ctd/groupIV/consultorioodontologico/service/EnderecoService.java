package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class EnderecoService {
    @Autowired
    IDao<Endereco> enderecoDaoH2;

    public Endereco cadastrar(Endereco endereco) throws SQLException {
        return enderecoDaoH2.cadastrar(endereco);
    }
}
