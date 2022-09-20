package com.dh.ctd.groupIV.consultorioodontologico.dao.impl;

import com.dh.ctd.groupIV.consultorioodontologico.dao.ConfiguracaoJDBC;
import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Endereco;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Configuration
public class EnderecoDAOH2 implements IDao<Endereco> {
    private ConfiguracaoJDBC configuracaoJDBC;

    //final static Logger log = Logger.getLogger(EnderecoDAOH2.class);

    @Override
    public Endereco cadastrar(Endereco endereco) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO endereco (logradouro, numero, complemento,bairro,municipio," +
                        "estado,cep,pais) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s');",
                endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(),
                endereco.getMunicipio(), endereco.getEstado(), endereco.getCep(), endereco.getPais());

        Connection connection = null;

        try {
            //log.info("Cadastrando endereço...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                endereco.setId(resultSet.getLong(1));

        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao cadastrar o endereço: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
        return endereco;
    }

    @Override
    public void alterar(Endereco enderecoUsuario) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLSelect = String.format("SELECT * FROM endereco WHERE id = '%s'", enderecoUsuario.getId());

        Connection connection = null;

        try {
            //log.info("Alterando endereço...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQLSelect);

            Endereco enderecoBanco = null;
            Endereco enderecoFinal = null;

            if (resultSet.next()) {
                enderecoBanco = criarObjetoEndereco(resultSet);
                enderecoFinal = compararEndereco(enderecoUsuario, enderecoBanco);

                String SQLUpdate = String.format("UPDATE endereco SET logradouro = '%s', numero = '%s', complemento = '%s',bairro = '%s',municipio = '%s', estado = '%s',cep = '%s',pais = '%s' WHERE id = '%s'",
                        enderecoFinal.getLogradouro(), enderecoFinal.getNumero(), enderecoFinal.getComplemento(), enderecoFinal.getBairro(),
                        enderecoFinal.getMunicipio(), enderecoFinal.getEstado(), enderecoFinal.getCep(), enderecoFinal.getPais(), enderecoFinal.getId());

                statement.execute(SQLUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao alterar o endereço: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
    }

    private Endereco criarObjetoEndereco(ResultSet result) throws SQLException {
        Long id = result.getLong("ID");
        String logradouro = result.getString("logradouro");
        String numero = result.getString("numero");
        String complemento = result.getString("complemento");
        String bairro = result.getString("bairro");
        String municipio = result.getString("municipio");
        String estado = result.getString("estado");
        String cep = result.getString("cep");
        String pais = result.getString("pais");

        return new Endereco(id, logradouro, numero, complemento, bairro, municipio, estado, cep, pais);
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

    @Override
    public void excluir(Endereco endereco) throws SQLException {
        //log.info("Abrindo conexão");

        String SQLDelete = String.format("DELETE FROM endereco WHERE id = '%s'", endereco.getId());

        Connection connection = null;

        try {
            //log.info("Excluindo endereço...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLDelete);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("Erro ao excluir o endereço: "+ e.getMessage());
        } finally {
            //log.info("Fechando conexão");
            if (connection != null) connection.close();
        }
    }

    @Override
    public List buscarTodos() {
        return null;
    }
}
