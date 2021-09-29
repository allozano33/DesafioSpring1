package com.desafiospring1.service;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.persistence.MedicoPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicoServiceTest {

    @Test
    void deveCadastrarMedico() throws IOException, ParseException {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
        ArrayList<Medico> lista = new ArrayList<>();
        Medico medico = new Medico("7771", "ze", "alves", "crmv-444441", "equinos");

        Mockito.when(mock.cadastra(Mockito.any(Medico.class))).thenReturn(medico);
        Mockito.when(mock.listagem()).thenReturn(lista);

        MedicoService medicoService = new MedicoService(mock);
        medicoService.cadastrar(medico);
        assertNotNull(medico.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumRegistroExistente() throws IOException, ParseException {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
        ArrayList<Medico> lista = new ArrayList<>();
        Medico medico = new Medico("7771", "ze", "alves", "crmv-444441", "equinos");

        lista.add(medico);
        Mockito.when(mock.cadastra(Mockito.any(Medico.class))).thenReturn(medico);
        Mockito.when(mock.listagem()).thenReturn(lista);

        MedicoService medicoService = new MedicoService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            medicoService.cadastrar(medico);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void deveListarMedico() throws ParseException, IOException {
////Cria Mock da dependência
//        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
//
//        //Instancia a classe sendo testada passando a dependência Mockada
//        MedicoService medicoService = new MedicoService();
//
//        //chama o método sendo testado
//        medicoService.listar();
//
//        //Verifica se o método da dependência foi chamado (incluindo o parâmetro exato)
//        Mockito.verify(mock).listagem();
//
//    }
}
