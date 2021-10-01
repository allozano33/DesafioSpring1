package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.MedicoPersistence;
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

    @Test
    void deveListarMedico() {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);

        MedicoService medicoService = new MedicoService(mock);

        medicoService.listar();

        Mockito.verify(mock).listagem();
        assertNotNull(medicoService.listar());
    }

    @Test
    void deveBuscarMedicoPorId() throws ParseException {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
        Medico medico = new Medico(1L, "3140", "wes", "alves", "crmv-55555", "aves");
        when(mock.buscaMedicoPorId(1L)).thenReturn(medico);

        MedicoService medicoService = new MedicoService(mock);
        Medico medico1 = medicoService.buscaMedicoPorId(1L);
        assertNotNull(medico1.getId());
    }

    @Test
    void deveDeletarMedico() throws IOException{
        MedicoPersistence mockMedicoPersistence = Mockito.mock(MedicoPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
        List<Medico> medicos = new ArrayList<>();

        Medico medico = new Medico();
        medico.setId(1L);
        medico.setCpf("123.254.256-77");
        medico.setNome("Joao");
        medico.setSobrenome("Silva");
        medico.setNumeroDeRegistro("CRMV-1234");
        medico.setEspecialidade("Clinico");

        medicos.add(medico);

        when(mockMedicoPersistence.deletaMedico(2L)).thenReturn(medicos);

        MedicoService medicoService = new MedicoService(mockMedicoPersistence, mockConsultaPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        List<Medico> medicos2 = medicoService.deletaMedico(2L);

        Assertions.assertNotNull(medicos2);
    }

    @Test
    void naoDeveDeletarMedicoEmConsulta() throws IOException {
        MedicoPersistence mockMedicoPersistence = Mockito.mock(MedicoPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
        List<Medico> medicos = new ArrayList<>();

        Medico medico = new Medico();
        medico.setId(1L);
        medico.setCpf("123.254.256-77");
        medico.setNome("Joao");
        medico.setSobrenome("Silva");
        medico.setNumeroDeRegistro("CRMV-1234");
        medico.setEspecialidade("Clinico");

        medicos.add(medico);

        when(mockMedicoPersistence.deletaMedico(1L)).thenReturn(medicos);

        MedicoService medicoService = new MedicoService(mockMedicoPersistence, mockConsultaPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            medicoService.deletaMedico(1L);
        });

        String expectedMessage = "Médico em consulta, ele não pode ser deletado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void deveAtualizarMedico() throws ParseException, IOException {
        MedicoPersistence mock = Mockito.mock(MedicoPersistence.class);
        Medico medico = new Medico(1L, "3140", "wes", "alves", "crmv-55555", "aves");

        MedicoService medicoService = new MedicoService(mock);

        when(mock.atualizaMedico(Mockito.any(Medico.class))).thenReturn(medico);

        medicoService.atualizaMedico(medico);
        Assertions.assertEquals("crmv-55555", medico.getNumeroDeRegistro());
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
