//package com.dh.ctd.groupIV.consultorioodontologico.dao.impl;
//
//import com.dh.ctd.groupIV.consultorioodontologico.dao.ConfiguracaoJDBC;
//import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
//import com.dh.ctd.groupIV.consultorioodontologico.model.Paciente;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//@Configuration
//public class PacienteDAOH2 implements IDao<Paciente> {
//
//    private ConfiguracaoJDBC configuracaoJDBC;
//
//    //final static Logger log = Logger.getLogger(PacienteDAOH2.class);
//    @Override
//    public Paciente cadastrar(Paciente paciente) throws SQLException {
//        //log.info("Abrindo conexão");
//
//        String SQLInsert = String.format("INSERT INTO paciente (nome, sobrenome, endereço,rg,dataDeCadastro" +
//                        "VALUES ('%s','%s','%s','%s','%s');",
//                paciente.getNome(), paciente.getSobrenome(), paciente.getEndereco(), paciente.getRg(),
//                paciente.getDataDeCadastro());
//
//        Connection connection = null;
//
//        try {
//            //log.info("Cadastrando paciente...");
//            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
//            connection = configuracaoJDBC.getConnection();
//            Statement statement = connection.createStatement();
//
//            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);
//
//            ResultSet resultSet = statement.getGeneratedKeys();
//
//            if (resultSet.next())
//                paciente.setId(resultSet.getLong(1));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //log.error("Erro ao cadastrar o paciente: "+ e.getMessage());
//        } finally {
//            //log.info("Fechando conexão");
//            if (connection != null) connection.close();
//        }
//        return paciente;
//    }
//
//    @Override
//    public void alterar(Paciente paciente) throws SQLException {
//
//    }
//
//    @Override
//    public void excluir(Paciente paciente) throws SQLException {
//        //log.info("Abrindo conexão");
//
//        String SQLDelete = String.format("DELETE FROM paciente WHERE id = '%s'", paciente.getId());
//
//        Connection connection = null;
//
//        try {
//            //log.info("Excluindo paciente...");
//            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
//            connection = configuracaoJDBC.getConnection();
//            Statement statement = connection.createStatement();
//
//            statement.execute(SQLDelete);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //log.error("Erro ao excluir o paciente: "+ e.getMessage());
//        } finally {
//            //log.info("Fechando conexão");
//            if (connection != null) connection.close();
//        }
//    }
//
//    @Override
//    public List<Paciente> buscarTodos() {
//        return null;
//    }
//}
