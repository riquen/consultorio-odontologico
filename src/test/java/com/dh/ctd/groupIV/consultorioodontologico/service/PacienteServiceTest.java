package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Endereco;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Paciente;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Nested
    @DisplayName("Testes para cadastrar Paciente")
    class PacienteServiceCadastroTest{
        @Test
        void cadastraPacienteComSucesso() throws CadastroInvalidoException {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente= new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);
            assertEquals(paciente, pacienteCadastrado);
        }
        @Test
        void cadastraPacienteComErroDeMatriculaDeCadastroJaExistente() throws CadastroInvalidoException {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente1= new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            pacienteService.cadastrar(paciente1);
            Endereco endereco2 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente2= new Paciente(null, "Henrique", "Arantes", endereco2, "123456", LocalDate.of(22,10,10));

            assertThrows(CadastroInvalidoException.class, ()->{
                pacienteService.cadastrar(paciente2);
            });
        }
    }
    @Nested
    @DisplayName("Testes para alterar Paciente")
    class PacienteServiceAlterarTest{
        @Test
        void alteraPacienteComSucesso() throws CadastroInvalidoException, ResourceNotFoundException {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente= new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);
            assertEquals(pacienteCadastrado.getNome(), "Henrique");
            Paciente paciente1 = new Paciente(pacienteCadastrado.getId(), "Marcos", null, null, null, null);
            Paciente pacienteAlterado = pacienteService.alterar(paciente1);
            assertEquals(pacienteAlterado.getNome(), "Marcos");
        }
        @Test
        void alteraPacienteComIDInexistenteRetornaNotFoundException() {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente2= new Paciente(new Long (-1), "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            assertThrows(ResourceNotFoundException.class, ()-> {
                pacienteService.alterar(paciente2);
            });
        }
    }
    @Nested
    @DisplayName("Teste para consulta de Paciente")
    class PacienteServiceConsultarTest{
        @Test
        void consultaPacientePorID() throws CadastroInvalidoException, ResourceNotFoundException {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente2= new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente2);
            Paciente pacienteConsultado = pacienteService.consultaPacientePorId(pacienteCadastrado.getId());
            assertEquals(paciente2, pacienteConsultado);
        }

        @Test
        void consultaPacientePorIDInexistenteRetornaResourceNotFound() {

            Long pacienteIdInexistente = new Long(-1);
            assertThrows(ResourceNotFoundException.class, () -> {
                pacienteService.consultaPacientePorId(pacienteIdInexistente);
            });
        }
        @Test
        void consultaListaDePacientes() throws CadastroInvalidoException {
                Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
                Paciente paciente1 = new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22, 10, 10));
                pacienteService.cadastrar(paciente1);
                Endereco endereco2 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
                Paciente paciente2 = new Paciente(null, "Henrique", "Arantes", endereco2, "123986", LocalDate.of(22, 10, 10));
                pacienteService.cadastrar(paciente2);
                List<Paciente> listaDePacientes = pacienteService.consultaPacientes();
                assertTrue(listaDePacientes.size()>=2);
        }
    }

    @Nested
    @DisplayName("Teste para excluir Paciente")
    class PacienteServiceExcluirTest{
        @Test
        void excluiPacientePorID() throws CadastroInvalidoException, ResourceNotFoundException {
            Endereco endereco1 = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente2= new Paciente(null, "Henrique", "Arantes", endereco1, "123456", LocalDate.of(22,10,10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente2);
            pacienteService.excluir(pacienteCadastrado.getId());
            assertThrows(ResourceNotFoundException.class, () -> {
                pacienteService.consultaPacientePorId(pacienteCadastrado.getId());
            });
        }

        @Test
        void excluiPacientePorIDInexistenteRetornaResourceNotFound() {
            Long pacienteIdInexistente = new Long(-1);
            assertThrows(ResourceNotFoundException.class, () -> {
                pacienteService.excluir(pacienteIdInexistente);
            });
        }
    }
}


