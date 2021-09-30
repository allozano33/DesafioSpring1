package com.desafiospring1.service;

import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ConsultaServiceTest {

    @Test
    void deveCadastrarConsulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ArrayList<Consulta> lista = new ArrayList<>();
        Consulta consulta = new Consulta("3140", LocalDateTime.parse("2015-08-04T10:11:30"), "Pata Quebrada", "Cirurgia", "Repouso", 2L, 2L);

        when(mock.cadastra(Mockito.any(Consulta.class))).thenReturn(consulta);
        when(mock.listagem()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);
        consultaService.cadastrar(consulta);
        assertNotNull(consulta.getId());
    }

    @Test
    void naoDeveCadastrarQuandoCodigoExistente() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ArrayList<Consulta> lista = new ArrayList<>();
        Consulta consulta = new Consulta("3140", LocalDateTime.parse("2015-08-04T10:11:30"), "Pata Quebrada", "Cirurgia", "Repouso", 2L, 2L);

        when(mock.cadastra(Mockito.any(Consulta.class))).thenReturn(consulta);
        when(mock.listagem()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            consultaService.cadastrar(consulta);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deveListarConsultas(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<Consulta> lista = consultas();
        when(mock.listagem()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);
        List<Consulta> listaRetorno = consultaService.listar();
        Assertions.assertEquals(lista,listaRetorno);
    }

    @Test
    void deveListarConsultaPorId(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        Consulta consulta = new Consulta(1L,"3140", LocalDateTime.parse("2015-08-04T10:11:30"), "Pata Quebrada", "Cirurgia", "Repouso", 2L, 2L);
        when(mock.buscaConsultaPorId(1L)).thenReturn(consulta);

        ConsultaService consultaService = new ConsultaService(mock);
        Consulta consulta1 = consultaService.buscaConsultaPorId(1L);
        assertNotNull(consulta1.getId());
    }

    private List<Consulta> consultas(){
        ArrayList<Consulta> consulta = new ArrayList<>();

        consulta.add(new Consulta(1L, "Cons-123", LocalDateTime.parse("2021-08-04T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));
        consulta.add(new Consulta(2L, "Cons-567", LocalDateTime.parse("2021-09-25T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));
        consulta.add(new Consulta(3L, "Cons-890", LocalDateTime.parse("2021-04-21T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));

        return consulta;
    }
}
