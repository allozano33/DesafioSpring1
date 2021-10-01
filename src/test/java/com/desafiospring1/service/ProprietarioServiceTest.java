package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.ProprietarioPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ProprietarioServiceTest {

    @Test
    void deveCadastrarProprietario() throws IOException, ParseException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario("54444444444", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");

        when(mock.cadastra(Mockito.any(Proprietario.class))).thenReturn(proprietario);
        when(mock.listagem()).thenReturn(lista);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        proprietarioService.cadastrar(proprietario);
        assertNotNull(proprietario.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumPacienteExistente() throws IOException, ParseException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        ArrayList<Proprietario> lista = new ArrayList<>();
        Proprietario proprietario = new Proprietario("44444444999", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");

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

    @Test
    void deveListarProprietario() throws ParseException, IOException {
        //Cria Mock da dependência
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);

        //Instancia a classe sendo testada passando a dependência Mockada
        ProprietarioService proprietarioService = new ProprietarioService(mock);

        //chama o método sendo testado
        proprietarioService.listar();

        //Verifica se o método da dependência foi chamado (incluindo o parâmetro exato)
        Mockito.verify(mock).listagem();
        assertNotNull(proprietarioService.listar());
    }

    @Test
    void deveVerificarProprietarioEmConsulta() throws IOException, ParseException {
        ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
        List<Proprietario> proprietarios = new ArrayList<>();

        Proprietario proprietario = new Proprietario();
        proprietario.setId(2L);
        proprietario.setCpf("145.258.658-99");
        proprietario.setNome("Maria");
        proprietario.setSobrenome("Alves");
        proprietario.setEndereco("Rua teste 123");
        proprietario.setTelefoneContato("159357852");
        proprietario.setDataDeNascimento(LocalDate.parse("1987-08-04"));

        proprietarios.add(proprietario);

        when(mockProprietarioPersistence.deletaProprietario(2L)).thenReturn(proprietarios);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence, mockConsultaPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            proprietarioService.deletaProprietario(2L);
        });

        String expectedMessage = "Proprietario em consulta, ele não pode ser deletado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deveBuscarProprietarioPorId() throws ParseException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        Proprietario proprietario = new Proprietario(1L,"44444444999", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");
        when(mock.buscaProprietarioPorId(1L)).thenReturn(proprietario);

        ProprietarioService proprietarioService = new ProprietarioService(mock);
        Proprietario proprietario1 = proprietarioService.buscaProprietarioPorId(1L);
        assertNotNull(proprietario1.getId());
    }


    @Test
    void deveDeletarProprietario() throws ParseException, IOException {

        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        List<Proprietario> lista = new ArrayList<>();

        ProprietarioService proprietarioService = new ProprietarioService(mock);

        when(mock.deletaProprietario(1L)).thenReturn(lista);
        Assertions.assertTrue(true);
    }

    //<--------------Precisa Ser TESTADO---------------->//
    @Test
    void naoDeveDeletarQuandoProprietarioEmConsulta() throws ParseException, IOException {
        ProprietarioPersistence mockProprietarioPersistence = Mockito.mock(ProprietarioPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
        List<Proprietario> proprietarios = new ArrayList<>();

        Proprietario proprietario = new Proprietario();
        proprietario.setId(2L);
        proprietario.setCpf("145.258.658-99");
        proprietario.setNome("Maria");
        proprietario.setSobrenome("Alves");
        proprietario.setEndereco("Rua teste 123");
        proprietario.setTelefoneContato("159357852");
        proprietario.setDataDeNascimento(LocalDate.parse("1987-08-04"));

        proprietarios.add(proprietario);

        when(mockProprietarioPersistence.deletaProprietario(2L)).thenReturn(proprietarios);

        ProprietarioService proprietarioService = new ProprietarioService(mockProprietarioPersistence, mockConsultaPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            proprietarioService.deletaProprietario(2L);
        });

        String expectedMessage = "Proprietario em consulta, ele não pode ser deletado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deveAtualizarProprietario() throws ParseException, IOException {
        ProprietarioPersistence mock = Mockito.mock(ProprietarioPersistence.class);
        Proprietario proprietario = new Proprietario(1L,"44444444999", "Joao", "Silva", LocalDate.parse("1963-06-08"), "Rua Europa, 30", "55555555");

        ProprietarioService proprietarioService = new ProprietarioService(mock);

        when(mock.atualizaProprietario(Mockito.any(Proprietario.class))).thenReturn(proprietario);

        proprietarioService.atualizaProprietario(proprietario);
        Assertions.assertEquals(1L, proprietario.getId());

    }

    private List<Consulta> consultas(){
        ArrayList<Consulta> consulta = new ArrayList<>();

        consulta.add(new Consulta(1L, "Cons-123", LocalDateTime.parse("2021-08-04T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));
        consulta.add(new Consulta(2L, "Cons-567", LocalDateTime.parse("2021-09-25T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));
        consulta.add(new Consulta(3L, "Cons-890", LocalDateTime.parse("2021-04-21T10:11:30"), "Teste", "teste", "tratamento", 1L, 2L));

        return consulta;
    }

    private List<ConsultaDto> consultasDto(){
        ArrayList<ConsultaDto> consultasDto = new ArrayList<>();

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
        proprietario.setNome("Maria");
        proprietario.setSobrenome("Alves");
        proprietario.setEndereco("Rua teste 123");
        proprietario.setTelefoneContato("159357852");
        proprietario.setDataDeNascimento(LocalDate.parse("1987-08-04"));

        AnimalDto animal = new AnimalDto();
        animal.setId(2L);
        animal.setNome("Mera");
        animal.setNumeroPaciente("Nu_12587");
        animal.setEspecie("Cao");
        animal.setRaca("Shi-tzu");
        animal.setCor("Branca");
        animal.setDataDeNascimento(LocalDate.parse("1987-08-04"));
        animal.setProprietario(proprietario);


        consultasDto.add(new ConsultaDto(1L, "Cons-123", LocalDateTime.parse("2021-08-04T10:11:30"), "Teste", "teste", "tratamento", medico, animal));
        consultasDto.add(new ConsultaDto(2L, "Cons-567", LocalDateTime.parse("2021-07-25T10:11:30"), "Teste", "teste", "tratamento", medico, animal));
        consultasDto.add(new ConsultaDto(3L, "Cons-890", LocalDateTime.parse("2021-09-22T10:11:30"), "Teste", "teste", "tratamento", medico, animal));

        return consultasDto;
    }
}