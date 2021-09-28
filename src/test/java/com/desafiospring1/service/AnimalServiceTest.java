package com.desafiospring1.service;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.persistence.AnimalPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalServiceTest {

    @Test
    void deveCadastrarAnimal() throws IOException {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        ArrayList<Animal> lista = new ArrayList<>();
        Animal animal = new Animal("55", "cao", "shi-tzu", "preto", LocalDate.parse("2009-11-30"), "Mockito", 1L);

        Mockito.when(mock.cadastra(Mockito.any(Animal.class))).thenReturn(animal);
        Mockito.when(mock.listagem()).thenReturn(lista);

        AnimalService animalService = new AnimalService();
        animalService.cadastrar(animal);
        assertNotNull(animal.getId());
    }

    @Test
    void naoDeveCadastrarQuandoNumPacienteExistente() throws IOException {
        AnimalPersistence mock = Mockito.mock(AnimalPersistence.class);
        ArrayList<Animal> lista = new ArrayList<>();
        Animal animal = new Animal("33", "gato", "siames", "branco", LocalDate.parse("2017-08-17"), "Amora", 3L);

        lista.add(animal);
        Mockito.when(mock.cadastra(Mockito.any(Animal.class))).thenReturn(animal);
        Mockito.when(mock.listagem()).thenReturn(lista);

        AnimalService animalService = new AnimalService();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            animalService.cadastrar(animal);
        });

        String expectedMessage = "Código já utilizado";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
