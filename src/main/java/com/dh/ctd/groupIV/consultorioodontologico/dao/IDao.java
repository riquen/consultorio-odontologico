package com.dh.ctd.groupIV.consultorioodontologico.dao;

import java.util.List;

public interface IDao<T> {
    T cadastrar(T t);
    void alterar(T t);
    void excluir(T t);
    List<T> buscarTodos();
}
