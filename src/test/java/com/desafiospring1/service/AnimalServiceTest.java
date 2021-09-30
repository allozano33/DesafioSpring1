package com.desafiospring1.service;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.AnimalPersistence;
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
    void deveAtualizarAnimal ( ) throws IOException {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        Animal animal = new Animal(1L, "434", "Gato", "Angora", "Branca", LocalDate.parse("2015-08-04"), "Leão", 43L);

        AnimalService consultaService = new AnimalService(mock);

        when(mock.atualizaAnimal(Mockito.any(Animal.class))).thenReturn(animal);

        consultaService.atualizaAnimal(animal);
        Assertions.assertEquals("1L", animal.getId());

    }

    @Test
    void deveListarAnimalPorId ( ) {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        List<Animal> lista = animal();
        when(mock.buscaAnimalPorId(2L)).thenReturn(lista.get(1));

        AnimalService animalService = new AnimalService(mock);
        Animal animal = AnimalService.buscaAnimalPorId(2L);
        Assertions.assertEquals(1L, animal.getId());
    }

    private List<Animal> animal ( ) {
        ArrayList<Animal> animal = new ArrayList<>();

        animal.add(new Animal(1L, "434", "Gato", "Angora", "Branca", LocalDate.parse("2015-08-04"), "Leão", 43L));
        animal.add(new Animal(2L, "435", "Cachorro", "Shi-Tzu", "Preta", LocalDate.parse("2015-08-04"), "Gabriell", 23L));
        animal.add(new Animal(3L, "436", "Gato", "Siames", "Amarela", LocalDate.parse("2015-08-04"), "Stella", 22L));

        return animal;
    }
}





