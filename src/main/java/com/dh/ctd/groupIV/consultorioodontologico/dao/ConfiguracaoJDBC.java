package com.dh.ctd.groupIV.consultorioodontologico.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracaoJDBC {
    private String jdbcDriver;
    private String dbUrl;
    private String usuario;
    private String senha;

    public ConfiguracaoJDBC(String jdbcDriver, String dbUrl, String usuario, String senha) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(this.jdbcDriver);
            connection = DriverManager.getConnection(this.dbUrl, this.usuario, this.senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
