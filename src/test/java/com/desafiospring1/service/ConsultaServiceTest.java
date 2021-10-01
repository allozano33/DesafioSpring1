package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
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
        List<Consulta> lista = new ArrayList<>();
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
        List<Consulta> lista = new ArrayList<>();
        Consulta consulta = new Consulta("3140", LocalDateTime.parse("2015-08-04T10:11:30"), "Pata Quebrada", "Cirurgia", "Repouso", 2L, 2L);

        lista.add(consulta);
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
        Assertions.assertEquals(3,listaRetorno.size());
    }

    @Test
    void deveListarConsultaPorId(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<Consulta> lista = consultas();
        when(mock.buscaConsultaPorId()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);
        Consulta consulta = consultaService.buscaConsultaPorId(2L);
        Assertions.assertNotNull(consulta);
    }

    @Test
    void deveListarNullConsultaPorId(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<Consulta> lista = consultas();
        when(mock.buscaConsultaPorId()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);
        Consulta consulta = consultaService.buscaConsultaPorId(4L);
        Assertions.assertNull(consulta);
    }

    @Test
    void deveAtualizarConsulta() throws IOException {
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        Consulta consulta = new Consulta(1L,"3140", LocalDateTime.parse("2015-08-04T10:11:30"), "Pata Quebrada", "Cirurgia", "Repouso", 2L, 2L);

        ConsultaService consultaService = new ConsultaService(mock);

        when(mock.atualizaConsulta(Mockito.any(Consulta.class))).thenReturn(consulta);

        consultaService.atualizaConsulta(consulta);
        Assertions.assertEquals("Pata Quebrada",consulta.getMotivo());
    }

    @Test
    void deveListarAnimalPorData(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<ConsultaDto> lista = consultasDto();

        when(mock.listagemCompleta()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);

        List<ConsultaDto> listaRetorno =  consultaService.listarAnimalPorData(2L);
        Assertions.assertEquals("Cons-567",listaRetorno.get(0).getCodigo());
    }

    @Test
    void deveListarDadosCompletos(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        ConsultaService consultaService = new ConsultaService(mock);

        consultaService.listarDadosCompletos();

        Mockito.verify(mock).listagemCompleta();
        assertNotNull(consultaService.listarDadosCompletos());
    }

    @Test
    void deveListarTotalDeConsultasPorMedico(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<ConsultaDto> consultas = consultasDto();

        when(mock.listagemCompleta()).thenReturn(consultas);

        ConsultaService consultaService = new ConsultaService(mock);

        List<ConsultaTotalMedicoDto> consultaTotalMedico =  consultaService.listarTotalDeConsultaPorMedico();

        Assertions.assertEquals(3,consultaTotalMedico.get(0).getTotal());
    }

    @Test
    void deveListarConsultasDoDia(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<ConsultaDto> lista = consultasDto();

        when(mock.listagemCompleta()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);

        List<ConsultaDto> listaRetorno =  consultaService.listarConsultasDoDia("01-10-2021");
        Assertions.assertEquals("Cons-890",listaRetorno.get(0).getCodigo());
    }

    @Test
    void deveListarConsultaOrdenadaPorNomeProprietario(){
        ConsultaPersistence mock = Mockito.mock(ConsultaPersistence.class);
        List<ConsultaDto> lista = consultasDto();

        when(mock.listagemCompleta()).thenReturn(lista);

        ConsultaService consultaService = new ConsultaService(mock);

        List<ConsultaDto> listaRetorno =  consultaService.listarPorNomeProprietario();
        Assertions.assertEquals("Wesley",listaRetorno.get(2).getAnimalDto().getProprietario().getNome());
    }

    private List<Consulta> consultas(){
        List<Consulta> consulta = new ArrayList<>();

        consulta.add(new Consulta(1L, "Cons-123", LocalDateTime.parse("2021-08-04T10:11:30"), "Dor de cabeca", "virose", "remedio e soro", 1L, 2L));
        consulta.add(new Consulta(2L, "Cons-567", LocalDateTime.parse("2021-09-25T10:11:30"), "Rotina", "prenha", "gestacao", 1L, 2L));
        consulta.add(new Consulta(3L, "Cons-890", LocalDateTime.parse("2021-04-21T10:11:30"), "Dor de barriga", "verme", "vermifugo", 1L, 2L));

        return consulta;
    }

    private List<ConsultaDto> consultasDto(){
        List<ConsultaDto> consultaDto = new ArrayList<>();

        Medico medico = new Medico();
        medico.setId(1L);
        medico.setCpf("123.254.256-77");
        medico.setNome("Joao");
        medico.setSobrenome("Silva");
        medico.setNumeroDeRegistro("CRMV-1234");
        medico.setEspecialidade("Clinico");

        Proprietario proprietario = new Proprietario();
        proprietario.setId(2L);
        proprietario.setCpf("145.258.658-99");
        proprietario.setNome("Wesley");
        proprietario.setSobrenome("Alves");
        proprietario.setEndereco("Rua teste 123");
        proprietario.setTelefoneContato("159357852");
        proprietario.setDataDeNascimento(LocalDate.parse("1987-08-04"));

        Proprietario proprietario2 = new Proprietario();
        proprietario2.setId(3L);
        proprietario2.setCpf("145.258.658-99");
        proprietario2.setNome("Ana");
        proprietario2.setSobrenome("Ferreira");
        proprietario2.setEndereco("Rua teste 123");
        proprietario2.setTelefoneContato("159357852");
        proprietario2.setDataDeNascimento(LocalDate.parse("1987-08-04"));

        AnimalDto animal = new AnimalDto();
        animal.setId(2L);
        animal.setNome("Mera");
        animal.setNumeroPaciente("NU-145");
        animal.setEspecie("Cao");
        animal.setRaca("Shi-tzu");
        animal.setCor("Branca");
        animal.setDataDeNascimento(LocalDate.parse("1987-08-04"));
        animal.setProprietario(proprietario);

        AnimalDto animal2 = new AnimalDto();
        animal2.setId(3L);
        animal2.setNome("Mera");
        animal2.setNumeroPaciente("NU-155");
        animal2.setEspecie("Gato");
        animal2.setRaca("Vira Lata");
        animal2.setCor("Cinza");
        animal2.setDataDeNascimento(LocalDate.parse("1987-08-04"));
        animal2.setProprietario(proprietario2);

        consultaDto.add(new ConsultaDto(1L, "Cons-123", LocalDateTime.parse("2021-08-04T10:11:30"), "Teste", "teste", "tratamento", medico, animal2));
        consultaDto.add(new ConsultaDto(2L, "Cons-567", LocalDateTime.parse("2021-07-25T10:11:30"), "Teste", "teste", "tratamento", medico, animal));
        consultaDto.add(new ConsultaDto(3L, "Cons-890", LocalDateTime.parse("2021-10-01T10:11:30"), "Teste", "teste", "tratamento", medico, animal2));

        return consultaDto;
    }
}
