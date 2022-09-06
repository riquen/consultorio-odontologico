package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco cadastrar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void alterar(Endereco enderecoUsuario) {
        Endereco enderecoBanco = enderecoRepository.getReferenceById(enderecoUsuario.getId());
        Endereco endereco = compararEndereco(enderecoUsuario, enderecoBanco);
        enderecoRepository.save(endereco);
    }

    public void excluir(Long enderecoId) {
        enderecoRepository.deleteById(enderecoId);
    }


    private Endereco compararEndereco(Endereco enderecoUsuario, Endereco enderecoBanco) {
        String logradouro = (enderecoUsuario.getLogradouro() != null) ? enderecoUsuario.getLogradouro() : enderecoBanco.getLogradouro();
        String numero = (enderecoUsuario.getNumero() != null) ? enderecoUsuario.getNumero() : enderecoBanco.getNumero();
        String complemento = (enderecoUsuario.getComplemento() != null) ? enderecoUsuario.getComplemento() : enderecoBanco.getComplemento();
        String bairro = (enderecoUsuario.getBairro() != null) ? enderecoUsuario.getBairro() : enderecoBanco.getBairro();
        String municipio = (enderecoUsuario.getMunicipio() != null) ? enderecoUsuario.getMunicipio() : enderecoBanco.getMunicipio();
        String estado = (enderecoUsuario.getEstado() != null) ? enderecoUsuario.getEstado() : enderecoBanco.getEstado();
        String cep = (enderecoUsuario.getCep() != null) ? enderecoUsuario.getCep() : enderecoBanco.getCep();
        String pais = (enderecoUsuario.getPais() != null) ? enderecoUsuario.getPais() : enderecoBanco.getPais();

        return new Endereco(enderecoUsuario.getId(), logradouro, numero, complemento, bairro, municipio, estado, cep, pais);
    }
}
