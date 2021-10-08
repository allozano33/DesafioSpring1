package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.persistence.AnimalPersistence;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalPersistence persistence;

    @Autowired
    private ConsultaPersistence persistenceConsulta;

    public AnimalService ( ) {
    }

    /**
     *
     * @param persistence - é esperado um parâmetro do tipo AnimalPersistence para injeção de dependêcia
     */
    public AnimalService (AnimalPersistence persistence) {
        this.persistence = persistence;
    }

    /**
     *
     * @param mockAnimalPersistence - é esperado um parâmetro do tipo AnimalPersistence para injeção de dependêcia
     * @param persistenceConsulta - é esperado um parâmetro do tipo ConsultaPersistence para injeção de dependêcia
     */
    public AnimalService (AnimalPersistence mockAnimalPersistence, ConsultaPersistence persistenceConsulta) {
        this.persistence = mockAnimalPersistence;
        this.persistenceConsulta = persistenceConsulta;
    }

    /**
     * @param numeroPaciente - é esperado um código de um Animal
     * @return Verificação de duplicidade de código
     * @author Grupo 5 - Tester Alessandro
     */
    private boolean codigoNaoUtilizado (String numeroPaciente) {
        for (Animal animal : persistence.listagem()) {
            if (animal.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param animal  - é esperado um objeto do tipo Animal
     * @return Animal cadastrada
     * @throws IOException - Lança exceção caso ocorra erro no cadastro de animal
     * @author Grupo 5 - Tester Alessandro
     */
    public Animal cadastrar (Animal animal) throws IOException {
        if (codigoNaoUtilizado(animal.getNumeroPaciente())) {
            animal.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(animal);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    /**
     * @return List de Animais do tipo Animal
     * @author Grupo 5 - Tester Alessandro
     */
    public List<Animal> listar ( ) {
        return persistence.listagem();
    }

    /**
     *@return list de animais do tipo AnimalDto
     * @author Grupo 5 - Tester Alessandro
     */
    public List<AnimalDto> listarDadosCompletos ( ) {
        return persistence.listagemCompleta();
    }

    /**
     *
     * @param id - é esperado um Id (LONG) de um Animal
     * @return List de Consultas do tipo ConsultaDTO do id do animal passado como parâmetro em ordem decrescente por DATA da consulta
     * @author Grupo 5 - Tester Alessandro
     */
    public Animal buscaAnimalPorId (Long id) {
        List<Animal> animais = persistence.listagem();

        for (Animal animal : animais) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    /**
     * @param id - é esperado um Id (LONG) de um Animal
     * @return List de Consultas do tipo ConsultaDTO do id do animal passado como parâmetro em consulta por lista completa
     * @author Grupo 5 - Tester Alessandro
     */
    private boolean animalEmConsulta (Long id) {
        for (ConsultaDto consultaDto : persistenceConsulta.listagemCompleta()) {
            if (consultaDto.getAnimalDto().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param id é esperado um objeto do tipo Animal
     * @return Animal  os seus dados alterados
     * @throws IOException - Lança exceção caso ocorra erro ao deletar animal que ja tenha tido uma consulta
     * @author Grupo 5 - Tester Alessandro
     */
    public List<Animal> deletaAnimal (Long id) throws IOException {
        if (!animalEmConsulta(id)) {
            return persistence.deletaAnimal(id);
        } else {
            throw new RuntimeException("Animal em consulta, ele não pode ser deletado!");
        }
    }

    /**
     *
     * @param animalDto é esperado um objeto do tipo AnimalDto
     * @return Animal com os seus dados atualizados
     * @throws IOException Lança exceção caso ocorra erro ao atualizar animal
     *  @author Grupo 5 - Tester Alessandro
     */
    public Animal atualizaAnimal (Animal animalDto) throws IOException {
        return persistence.atualizaAnimal(animalDto);
    }
}
