package com.dh.ctd.groupIV.consultorioodontologico.dao.impl;

import com.dh.ctd.groupIV.consultorioodontologico.dao.ConfiguracaoJDBC;
import com.dh.ctd.groupIV.consultorioodontologico.dao.IDao;
import com.dh.ctd.groupIV.consultorioodontologico.model.Endereco;
import org.apache.log4j.Logger;
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
                endereco.getLogradouro(),endereco.getNumero(),endereco.getComplemento(),endereco.getBairro(),
                endereco.getMunicipio(),endereco.getEstado(),endereco.getCep(),endereco.getPais());

        Connection connection = null;

        try{
            //log.info("Cadastrando endereço...");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/consultorio-odontologico;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                endereco.setId(resultSet.getInt(1));

        }catch (Exception e){
            e.printStackTrace();
            //log.error("Erro ao cadastrar o endereço: "+ e.getMessage());
        }finally {
            //log.info("Fechando conexão");
            connection.close();
        }
        return endereco;
    }

    @Override
    public void alterar(Endereco endereco) {

    }

    @Override
    public void excluir(Endereco endereco) {

    }


    @Override
    public List buscarTodos() {
        return null;
    }
}
