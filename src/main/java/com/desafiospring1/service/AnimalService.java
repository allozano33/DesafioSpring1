package com.desafiospring1.service;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.persistence.AnimalPersistence;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private AnimalPersistence persistence = new AnimalPersistence();

    private boolean codigoNaoUtilizado(String numeroPaciente) {
        for (Animal animal : persistence.listagem()) {
            if (animal.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    public Animal cadastra(Animal animal) {
        if (codigoNaoUtilizado(animal.getNumeroPaciente())) {
            animal.setId(persistence.listagem().size()+1L);
            return persistence.cadastra(animal);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }
}
