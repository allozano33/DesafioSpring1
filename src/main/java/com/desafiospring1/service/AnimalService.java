package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.persistence.AnimalPersistence;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class AnimalService {

    public static Animal buscaAnimalPorId;
    private static AnimalPersistence persistence;

    public AnimalService() {

    }

    public AnimalService(AnimalPersistence persistence) {
        this.persistence = persistence;
    }

    public AnimalService (AnimalPersistence mockAnimalPersistence, AnimalPersistence mockAnimalPersistence1) {
    }

    private boolean codigoNaoUtilizado(String numeroPaciente) {
        for (Animal animal : persistence.listagem()) {
            if (animal.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    public Animal cadastrar(Animal animal) throws IOException {
        if (codigoNaoUtilizado(animal.getNumeroPaciente())) {
            animal.setId(persistence.listagem().size()+1L);
            return persistence.cadastra(animal);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<Animal> listar() {
        return persistence.listagem();
    }

    public List<AnimalDto> listarDadosCompletos() {
        return persistence.listagemCompleta();
    }

    public static Animal buscaAnimalPorId (Long id) {
        return persistence.buscaAnimalPorId(id);
    }

    private boolean animalEmConsulta(Long id) {
        ConsultaPersistence consultaPersistence = new ConsultaPersistence();
        for (ConsultaDto consultaDto : consultaPersistence.listagemCompleta()) {
            if (consultaDto.getAnimalDto().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<Animal> deletaAnimal(Long id) throws IOException {
        if(!animalEmConsulta(id)) {
            return persistence.deletaAnimal(id);
        } else {
            throw new RuntimeException("Animal em consulta, ele não pode ser deletado!");
        }
    }

    public static Animal atualizaAnimal (Animal animalDto) throws IOException {
        return persistence.atualizaAnimal(animalDto);
    }
}
