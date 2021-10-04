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

    private AnimalPersistence persistence = new AnimalPersistence();
    private ConsultaPersistence persistenceConsulta;

    public AnimalService() {

    }

    public AnimalService(AnimalPersistence persistence) {
        this.persistence = persistence;
    }

    public AnimalService(AnimalPersistence mockAnimalPersistence, ConsultaPersistence persistenceConsulta) {
        this.persistence = mockAnimalPersistence;
        this.persistenceConsulta = persistenceConsulta;
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

    public Animal buscaAnimalPorId(Long id) {
        List<Animal> animais = persistence.listagem();

        for (Animal animal : animais) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    private boolean animalEmConsulta(Long id) {
        for (ConsultaDto consultaDto : persistenceConsulta.listagemCompleta()) {
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

    public Animal atualizaAnimal (Animal animalDto) throws IOException {
        return persistence.atualizaAnimal(animalDto);
    }
}
