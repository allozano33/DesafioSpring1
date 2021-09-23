package com.desafiospring1.service;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.persistence.AnimalPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private AnimalPersistence persistence = new AnimalPersistence();

    private boolean codigoNaoUtilizado(String numeroPaciente) {
        for (AnimalDto animalDto : persistence.listagem()) {
            if (animalDto.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    public AnimalDto cadastrar(AnimalDto animalDto) {
        if (codigoNaoUtilizado(animalDto.getNumeroPaciente())) {
            animalDto.setId(persistence.listagem().size()+1L);
            return persistence.cadastra(animalDto);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<AnimalDto> listar() {
        return persistence.listagem();
    }

    public AnimalDto buscaAnimalPorId(Long id) {
        return persistence.buscaAnimalPorId(id);
    }

    public List<AnimalDto> deletaAnimal(Long id) {

        return persistence.deletaAnimal(id);
    }

    public AnimalDto atualizaAnimal(AnimalDto animalDto){
        return persistence.atualizaAnimal(animalDto);
    }
}
