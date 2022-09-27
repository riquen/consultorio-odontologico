package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
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
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ConsultaServiceTest {
    @Autowired
    ConsultaService consultaService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    DentistaService dentistaService;

    @Nested
    @DisplayName("Testes para cadastrar Consulta")
    class ConsultaServiceCadastroTest {
        @Test
        void cadastraConsultaComSucesso() throws CadastroInvalidoException {

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaCadastrado, dataHoraConsulta);

            Consulta consultaCadastrada = consultaService.cadastrar(consulta);
            assertEquals(consulta, consultaCadastrada);
        }

        @Test
        void cadastraConsultaComPacienteInexistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            Paciente pacienteIdInexistente = new Paciente(new Long(-1), null, null, null, null, null);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteIdInexistente, dentistaCadastrado, dataHoraConsulta);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.cadastrar(consulta);
            });
        }

        @Test
        void cadastraConsultaComDentistaInexistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Dentista dentistaIdInexistente = new Dentista(new Long(-1), null, null, null);

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaIdInexistente, dataHoraConsulta);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.cadastrar(consulta);
            });
        }

        @Test
        void cadastraConsultaComMesmoDentistaEHorarioJaExistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Endereco enderecoPrimeiroPaciente = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacientePrimeiraConsulta = new Paciente(null, "Henrique", "Arantes", enderecoPrimeiroPaciente, "123456", LocalDate.of(22, 10, 10));
            pacientePrimeiraConsulta = pacienteService.cadastrar(pacientePrimeiraConsulta);

            Endereco enderecoSegundoPaciente = new Endereco(null, "Rua Brasil", "12345", null, "Legal", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacienteSegundaConsulta = new Paciente(null, "Paulo", "Nunes", enderecoSegundoPaciente, "12453456", LocalDate.of(22, 10, 10));
            pacienteSegundaConsulta = pacienteService.cadastrar(pacienteSegundaConsulta);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta primeiraConsulta = new Consulta(null, pacientePrimeiraConsulta, dentistaCadastrado, dataHoraConsulta);
            Consulta segundaConsulta = new Consulta(null, pacienteSegundaConsulta, dentistaCadastrado, dataHoraConsulta);

            Consulta consultaCadastrada = consultaService.cadastrar(primeiraConsulta);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.cadastrar(segundaConsulta);
            });
        }
    }

    @Nested
    @DisplayName("Testes para alterar Consulta")
    class ConsultaServiceAlterarTest {
        @Test
        void alteraConsultaComSucesso() throws CadastroInvalidoException, ResourceNotFoundException {

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraConsultaOriginal = LocalDateTime.of(2022, 9, 25, 8, 30);
            LocalDateTime dataHoraConsultaAlterada = LocalDateTime.of(2022, 9, 25, 9, 30);

            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaCadastrado, dataHoraConsultaOriginal);
            Consulta consultaCadastrada = consultaService.cadastrar(consulta);
            assertEquals(consultaCadastrada.getDataHora(), dataHoraConsultaOriginal);

            Consulta consultaAlterada = new Consulta(consultaCadastrada.getId(), null, null, dataHoraConsultaAlterada);
            Consulta consultaCadastradaAlterada = consultaService.alterar(consultaAlterada);
            assertEquals(consultaCadastradaAlterada.getDataHora(), dataHoraConsultaAlterada);
        }

        @Test
        void alteraConsultaComIDInexistenteRetornaResourceNotFoundException() {
            LocalDateTime dataHoraConsultaAlterada = LocalDateTime.of(2022, 9, 25, 9, 30);
            Consulta consulta = new Consulta(new Long(-1), null, null, dataHoraConsultaAlterada);
            assertThrows(ResourceNotFoundException.class, () -> {
                consultaService.alterar(consulta);
            });
        }

        @Test
        void alteraConsultaComPacienteInexistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacienteExistente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(pacienteExistente);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaCadastrado, dataHoraConsulta);
            Consulta consultaCadastrada = consultaService.cadastrar(consulta);

            Paciente pacienteIdInexistente = new Paciente(new Long(-1), null, null, null, null, null);
            Consulta consultaAlterada = new Consulta(consultaCadastrada.getId(), pacienteIdInexistente, null, null);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.alterar(consultaAlterada);
            });
        }

        @Test
        void alteraConsultaComDentistaInexistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacienteExistente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(pacienteExistente);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaCadastrado, dataHoraConsulta);
            Consulta consultaCadastrada = consultaService.cadastrar(consulta);

            Dentista dentistaIdInexistente = new Dentista(new Long(-1), null, null, null);
            Consulta consultaAlterada = new Consulta(consultaCadastrada.getId(), null, dentistaIdInexistente, null);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.alterar(consultaAlterada);
            });
        }

        @Test
        void alteraConsultaComMesmoDentistaEHorarioJaExistenteRetornaCadastroInvalido() throws CadastroInvalidoException {

            Endereco enderecoPrimeiroPaciente = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacientePrimeiraConsulta = new Paciente(null, "Henrique", "Arantes", enderecoPrimeiroPaciente, "123456", LocalDate.of(22, 10, 10));
            pacientePrimeiraConsulta = pacienteService.cadastrar(pacientePrimeiraConsulta);

            Endereco enderecoSegundoPaciente = new Endereco(null, "Rua Brasil", "12345", null, "Legal", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacienteSegundaConsulta = new Paciente(null, "Paulo", "Nunes", enderecoSegundoPaciente, "12453456", LocalDate.of(22, 10, 10));
            pacienteSegundaConsulta = pacienteService.cadastrar(pacienteSegundaConsulta);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraPrimeiraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            LocalDateTime dataHoraSegundaConsulta = LocalDateTime.of(2022, 9, 25, 9, 30);

            Consulta primeiraConsulta = new Consulta(null, pacientePrimeiraConsulta, dentistaCadastrado, dataHoraPrimeiraConsulta);
            Consulta segundaConsulta = new Consulta(null, pacienteSegundaConsulta, dentistaCadastrado, dataHoraSegundaConsulta);

            consultaService.cadastrar(primeiraConsulta);
            Consulta segundaConsultaCadastrada = consultaService.cadastrar(segundaConsulta);

            Consulta segundaConsultaAlterada = new Consulta(segundaConsultaCadastrada.getId(), null, null, dataHoraPrimeiraConsulta);

            assertThrows(CadastroInvalidoException.class, () -> {
                consultaService.alterar(segundaConsultaAlterada);
            });
        }
    }

    @Nested
    @DisplayName("Teste para consulta de Consulta")
    class ConsultaServiceConsultarTest {
        @Test
        void consultaConsultaPorID() throws CadastroInvalidoException, ResourceNotFoundException {

            Endereco endereco = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente paciente = new Paciente(null, "Henrique", "Arantes", endereco, "123456", LocalDate.of(22, 10, 10));
            Paciente pacienteCadastrado = pacienteService.cadastrar(paciente);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            Consulta consulta = new Consulta(null, pacienteCadastrado, dentistaCadastrado, dataHoraConsulta);

            Consulta consultaCadastrada = consultaService.cadastrar(consulta);

            Consulta consultaConsultada = consultaService.consultaConsultaPorId(consultaCadastrada.getId());
            assertEquals(consulta, consultaConsultada);
        }

        @Test
        void consultaConsultaPorIDInexistenteRetornaResourceNotFound() {

            Long consultaIdInexistente = new Long(-1);
            assertThrows(ResourceNotFoundException.class, () -> {
                consultaService.consultaConsultaPorId(consultaIdInexistente);
            });

        }
        @Test
        void consultaListaDeConsultas() throws CadastroInvalidoException {
            Endereco enderecoPrimeiroPaciente = new Endereco(null, "Rua Uruguai", "1234", "casa", "centro", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacientePrimeiraConsulta = new Paciente(null, "Henrique", "Arantes", enderecoPrimeiroPaciente, "123456", LocalDate.of(22, 10, 10));
            pacientePrimeiraConsulta = pacienteService.cadastrar(pacientePrimeiraConsulta);

            Endereco enderecoSegundoPaciente = new Endereco(null, "Rua Brasil", "12345", null, "Legal", "Porto Alegre", "RS", "99053874", "Brazil");
            Paciente pacienteSegundaConsulta = new Paciente(null, "Paulo", "Nunes", enderecoSegundoPaciente, "12453456", LocalDate.of(22, 10, 10));
            pacienteSegundaConsulta = pacienteService.cadastrar(pacienteSegundaConsulta);

            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);

            LocalDateTime dataHoraPrimeiraConsulta = LocalDateTime.of(2022, 9, 25, 8, 30);
            LocalDateTime dataHoraSegundaConsulta = LocalDateTime.of(2022, 9, 25, 9, 30);

            Consulta primeiraConsulta = new Consulta(null, pacientePrimeiraConsulta, dentistaCadastrado, dataHoraPrimeiraConsulta);
            Consulta segundaConsulta = new Consulta(null, pacienteSegundaConsulta, dentistaCadastrado, dataHoraSegundaConsulta);
            consultaService.cadastrar(primeiraConsulta);
            consultaService.cadastrar(segundaConsulta);
            List<Consulta> listaDeConsultas = consultaService.consultaConsultas();
            assertTrue(listaDeConsultas.size()>=2);
        }
    }
}