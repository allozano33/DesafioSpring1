package com.desafiospring1.service;

import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsultaServiceTest {

    @Test
    void deveCadastrarConsulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ArrayList<Consulta> lista = new ArrayList<>();
        Consulta consulta = new Consulta("34", LocalDateTime.parse("2015-08-04T10:11:30"),"Pata Quebrada","Cirurgia","Repouso",2L,2L);

        Mockito.when(mock.cadastra(Mockito.any(Consulta.class))).thenReturn(consulta);
        Mockito.when(mock.listagem()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService();
        consultaService.cadastrar(consulta);
        assertNotNull(consulta.getId());

    }

    @Test
    void naoDeveCadastrarQuandoCodigoExistente() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ArrayList<Consulta> lista = new ArrayList<>();
        Consulta consulta = new Consulta("34", LocalDateTime.parse("2015-08-04T10:11:30"),"Pata Quebrada","Cirurgia","Repouso",2L,2L);

        Mockito.when(mock.cadastra(Mockito.any(Consulta.class))).thenReturn(consulta);
        Mockito.when(mock.listagem()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            consultaService.cadastrar(consulta);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
