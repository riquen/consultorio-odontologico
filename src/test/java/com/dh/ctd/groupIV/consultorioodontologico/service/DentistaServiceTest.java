package com.dh.ctd.groupIV.consultorioodontologico.service;

import com.dh.ctd.groupIV.consultorioodontologico.entity.Dentista;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.CadastroInvalidoException;
import com.dh.ctd.groupIV.consultorioodontologico.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DentistaServiceTest {
    @Autowired
    DentistaService dentistaService;

    @Nested
    @DisplayName("Testes para cadastrar Dentista")
    class DentistaServiceCadastroTest{
        @Test
        void cadastraDentistaComSucesso() throws CadastroInvalidoException {
            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055362");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);
            assertEquals(dentista, dentistaCadastrado);
        }
        @Test
        void cadastraDentistaComErroDeMatriculaDeCadastroJaExistente() throws CadastroInvalidoException {
            Dentista dentista1 = new Dentista(null, "Henrique", "Arantes", "055361");
            dentistaService.cadastrar(dentista1);
            Dentista dentista2 = new Dentista(null, "Marcos", "Castro", "055361");

            assertThrows(CadastroInvalidoException.class, ()->{
                dentistaService.cadastrar(dentista2);
            });
        }
    }
    @Nested
    @DisplayName("Testes para alterar Dentista")
    class DentistaServiceAlterarTest{
        @Test
        void alteraDentistaComSucesso() throws CadastroInvalidoException, ResourceNotFoundException {
            Dentista dentista = new Dentista(null, "Henrique", "Arantes", "055360");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista);
            assertEquals(dentistaCadastrado.getNome(), "Henrique");
            Dentista dentista1 = new Dentista(dentistaCadastrado.getId(), "Marcos", null, null);
            Dentista dentistaAlterado = dentistaService.alterar(dentista1);
            assertEquals(dentistaAlterado.getNome(), "Marcos");
        }
        @Test
        void alteraDentistaComIDInexistenteRetornaNotFoundException() {
            Dentista dentista2 = new Dentista(new Long (-1), "Henrique", "Arantes", "057361");
            assertThrows(ResourceNotFoundException.class, ()-> {
                dentistaService.alterar(dentista2);
            });
        }
    }
    @Nested
    @DisplayName("Teste para consulta de Dentista")
    class DentistaServiceConsultarTest{
        @Test
        void consultaDentistaPorID() throws CadastroInvalidoException, ResourceNotFoundException {
            Dentista dentista2 = new Dentista(null, "Paulo", "Lopes", "058961");
            Dentista dentistaCadastrado = dentistaService.cadastrar(dentista2);
            Dentista dentistaConsultado = dentistaService.consultaDentistaPorId(dentistaCadastrado.getId());
            assertEquals(dentista2, dentistaConsultado);
        }

        @Test
        void consultaDentistaPorIDInexistenteRetornaResourceNotFound() {

            Long dentistaIdInexistente = new Long(-1);
            assertThrows(ResourceNotFoundException.class, () -> {
                dentistaService.consultaDentistaPorId(dentistaIdInexistente);
            });
        }
        @Test
        void consultaListaDeDentistas() throws CadastroInvalidoException {
            Dentista dentista1 = new Dentista(null, "Henrique", "Arantes", "123456");
            dentistaService.cadastrar(dentista1);
            Dentista dentista2 = new Dentista(null, "Henrique", "Arantes", "123986");
            dentistaService.cadastrar(dentista2);
            List<Dentista> listaDeDentistas = dentistaService.consultaDentistas();
            assertTrue(listaDeDentistas.size()>=2);
        }
    }
}