package com.dh.ctd.groupIV.consultorioodontologico.dao.impl;

import com.dh.ctd.groupIV.consultorioodontologico.dao.ConfiguracaoJDBC;
import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.model.Consulta;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Configuration
public class ConsultaDAOH2 implements IDao<Consulta> {

    private ConfiguracaoJDBC configuracaoJDBC;

    //final static Logger log = Logger.getLogger(ConsultaDAOH2.class);
    @Override
    public Consulta cadastrar(Consulta consulta) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO consulta (paciente, dentista, dataHora" +
                        "VALUES ('%s','%s','%s');",
                consulta.getPaciente(), consulta.getDentista(), consulta.getDataHora());

        Connection connection = null;

        try {
            //log.info("Cadastrando consulta...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                consulta.setId(resultSet.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao cadastrar a consulta: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
        return consulta;
    }

    @Override
    public void alterar(Consulta consulta) throws SQLException {

    }

    @Override
    public void excluir(Consulta consulta) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLDelete = String.format("DELETE FROM paciente WHERE id = '%s'", consulta.getId());

        Connection connection = null;

        try {
            //log.info("Excluindo consulta...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLDelete);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao excluir a consulta: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<Consulta> buscarTodos() {
        return null;
    }
}
