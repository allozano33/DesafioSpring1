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
        Medico medico = new Medico("777", "ze", "alves", "crmv-44444", "equinos");

        Mockito.when(mock.cadastra(Mockito.any(Medico.class))).thenReturn(medico);
        Mockito.when(mock.listagem()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();
        medicoService.cadastrar(medico);
        assertNotNull(medico.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumRegistroExistente() throws IOException, ParseException {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
        ArrayList<Medico> lista = new ArrayList<>();
        Medico medico = new Medico("777", "ze", "alves", "crmv-44444", "equinos");

        lista.add(medico);
        Mockito.when(mock.cadastra(Mockito.any(Medico.class))).thenReturn(medico);
        Mockito.when(mock.listagem()).thenReturn(lista);

        MedicoService medicoService = new MedicoService();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            medicoService.cadastrar(medico);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
