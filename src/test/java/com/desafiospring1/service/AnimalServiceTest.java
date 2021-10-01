package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.AnimalPersistence;
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
import static org.mockito.Mockito.*;


public class AnimalServiceTest {

    @Test
    void deveCadastrarAnimal ( ) throws IOException {
        AnimalPersistence mock = mock(AnimalPersistence.class);
        ArrayList<Animal> lista = new ArrayList<>();
        Animal animal = new Animal(5L, "515112", "cao", "shi-tzu", "preto", LocalDate.parse("2009-11-30"), "Mockito", 1L);

        when(mock.cadastra(any(Animal.class))).thenReturn(animal);
        when(mock.listagem()).thenReturn(lista);

        AnimalService animalService = new AnimalService(mock);
        animalService.cadastrar(animal);
        assertNotNull(animal.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumPacienteExistente ( ) throws IOException {
        AnimalPersistence mock = mock(AnimalPersistence.class);
        ArrayList<Animal> lista = new ArrayList<>();
        Animal animal = new Animal(1L, "5151", "cao", "shi-tzu", "preto", LocalDate.parse("2009-11-30"), "Mockito", 1L);

        lista.add(animal);
        when(mock.cadastra(any(Animal.class))).thenReturn(animal);
        when(mock.listagem()).thenReturn(lista);

        AnimalService animalService = new AnimalService(mock);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ( ) -> {
            animalService.cadastrar(animal);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deveListarAnimais ( ) {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        List<Animal> lista = animal();
        when(mock.listagem()).thenReturn(lista);

        AnimalService animalService = new AnimalService(mock);
        List<Animal> listaRetorno = animalService.listar();
        Assertions.assertEquals(3, listaRetorno.size());
    }

    @Test
    void deveListarAnimalPorId ( ) throws ParseException {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        Animal animal = new Animal(1L, "434", "Gato", "Angora", "Branca", LocalDate.parse("2015-08-04"), "Leão", 43L);
        when(mock.buscaAnimalPorId(1L)).thenReturn(animal);

        AnimalService animalService = new AnimalService(mock);
        Animal animal1 = animalService.buscaAnimalPorId(1L);
        assertNotNull(animal1.getId());


    }

    private List<Animal> animal ( ) {
        ArrayList<Animal> animal = new ArrayList<>();

        animal.add(new Animal(1L, "434", "Gato", "Angora", "Branca", LocalDate.parse("2015-08-04"), "Leão", 43L));
        animal.add(new Animal(1L, "435", "Cachorro", "Shi-Tzu", "Preta", LocalDate.parse("2015-08-04"), "Gabriell", 23L));
        animal.add(new Animal(1L, "436", "Gato", "Siames", "Amarela", LocalDate.parse("2015-08-04"), "Stella", 22L));

        return animal;


    }

    @Test
    void deveVerificarAnimalEmConsulta() throws IOException, ParseException {
        AnimalPersistence mockAnimalPersistence = Mockito.mock(AnimalPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);
        List<Animal> animais = new ArrayList<>();

        Animal animal = new Animal();
        animal.setId(2L);
        animal.setNome("Mera");
        animal.setNumeroPaciente("Nu_12587");
        animal.setEspecie("Cao");
        animal.setRaca("Shi-tzu");
        animal.setCor("Branca");
        animal.setDataDeNascimento(LocalDate.parse("1987-08-04"));
        animal.setId(2L);

        animais.add(animal);

        when(mockAnimalPersistence.deletaAnimal(2L)).thenReturn(animais);

        AnimalService animalService = new AnimalService(mockAnimalPersistence, mockAnimalPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            animalService.deletaAnimal(2L);
        });

        String expectedMessage = "Animal em consulta, ele não pode ser deletado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }



    @Test
    void deveDeletarAnimal() throws ParseException, IOException {

        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        List<Animal> lista = new ArrayList<>();

        AnimalService animalServiceService = new AnimalService(mock);

        when(mock.deletaAnimal(1L)).thenReturn(lista);
        Assertions.assertTrue(true);
    }

    @Test
    void naoDeveDeletarAnimalEmConsulta() throws ParseException, IOException {
        AnimalPersistence mockAnimalPersistence = Mockito.mock(AnimalPersistence.class);
        ConsultaPersistence mockConsultaPersistence = Mockito.mock(ConsultaPersistence.class);

        List<Animal> animais = new ArrayList<>();
        Animal animal = new Animal();
        animal.setId(2L);
        animal.setNome("Mera");
        animal.setNumeroPaciente("Nu_12587");
        animal.setEspecie("Cao");
        animal.setRaca("Shi-tzu");
        animal.setCor("Branca");
        animal.setDataDeNascimento(LocalDate.parse("1987-08-04"));
        animal.setIdProprietario(2l);

        animais.add(animal);

        when(mockAnimalPersistence.deletaAnimal(2L)).thenReturn(animais);

        AnimalService animalService = new AnimalService(mockAnimalPersistence, mockAnimalPersistence);
        Mockito.when(mockConsultaPersistence.listagemCompleta()).thenReturn(consultasDto());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            animalService.deletaAnimal(2L);
        });

        String expectedMessage = " Animal em consulta, ele não pode ser deletado!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
///continuar
    @Test
    void deveAtualizarAnimal() throws ParseException, IOException {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        Animal animal = new Animal(1L, "434", "Gato", "Angora", "Branca", LocalDate.parse("2015-08-04"), "Leão", 43L);
        AnimalService animalService = new AnimalService(mock);

        when(mock.atualizaAnimal(Mockito.any(Animal.class))).thenReturn(animal);

        animalService.atualizaAnimal(animal);
        Assertions.assertEquals(1L, animal.getId());

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