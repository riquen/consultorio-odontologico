package com.dh.ctd.groupIV.consultorioodontologico.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    T cadastrar(T t) throws SQLException;
    void alterar(T t) throws SQLException;
    void excluir(T t) throws SQLException;
    List<T> buscarTodos();
}
