package com.desafiospring1.service;

import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ProprietarioPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProprietarioServiceTest {

    @Test
    void deveCadastrarProprietario() throws IOException, ParseException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario("54444444444", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");

        Mockito.when(mock.cadastra(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mock.listagem()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        proprietarioService.cadastrar(proprietario);
        assertNotNull(proprietario.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumPacienteExistente() throws IOException, ParseException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario("44444444444", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");

        lista.add(proprietario);
        Mockito.when(mock.cadastra(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        Mockito.when(mock.listagem()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            proprietarioService.cadastrar(proprietario);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
