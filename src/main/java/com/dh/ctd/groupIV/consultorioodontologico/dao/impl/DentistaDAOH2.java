package com.dh.ctd.groupIV.consultorioodontologico.dao.impl;

import com.dh.ctd.groupIV.consultorioodontologico.dao.ConfiguracaoJDBC;
import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.model.Dentista;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;

    //final static Logger log = Logger.getLogger(DentistaDAOH2.class);
    @Override
    public Dentista cadastrar(Dentista dentista) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO dentista (nome, sobrenome, matriculaDeCadastro" +
                        "VALUES ('%s','%s','%s');",
                dentista.getNome(), dentista.getSobrenome(), dentista.getMatriculaDeCadastro());

        Connection connection = null;

        try {
            //log.info("Cadastrando dentista...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                dentista.setId(resultSet.getLong(1));

        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao cadastrar o dentista: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
        return dentista;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {

    }

    @Override
    public void excluir(Dentista dentista) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLDelete = String.format("DELETE FROM dentista WHERE id = '%s'", dentista.getId());

        Connection connection = null;

        try {
            //log.info("Excluindo dentista...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLDelete);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao excluir o dentista: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<Dentista> buscarTodos() {
        return null;
    }
}
