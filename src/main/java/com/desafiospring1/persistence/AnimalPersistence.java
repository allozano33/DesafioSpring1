package com.desafiospring1.persistence;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.util.AnimalJson;

import java.util.ArrayList;
import java.util.List;

public class AnimalPersistence {

    private AnimalJson animalJson = new AnimalJson();
    private static List<Animal> listaAnimais = new ArrayList<>();

    public Animal cadastra(Animal animal) {
        animal.setId(animalJson.listar().size() + 1L);
        List<Animal> novaListaAnimais = animalJson.listar();
        novaListaAnimais.add(animal);
        animalJson.manipularJson(novaListaAnimais);
        return animal;
    }

    public List<Animal> listagem() {
        return animalJson.listar();
    }

    public List<AnimalDto> listagemCompleta() {
        return animalJson.listarDadosCompletos();
    }

    public Animal buscaAnimalPorId(Long id) {
        for (Animal animal : animalJson.listar()) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    public List<Animal> deletaAnimal(Long id) {
        listaAnimais = animalJson.listar();
        for(int i=0; i<listaAnimais.size();i++){
            if(listaAnimais.get(i).getId().equals(id)){
                listaAnimais.remove(i);
            }
        }
        animalJson.manipularJson(listaAnimais);
        return animalJson.listar();
    }

    public Animal atualizaAnimal(Animal animal){
        listaAnimais = animalJson.listar();
        for (int i = 0; i < listaAnimais.size(); i++) {
            if (listaAnimais.get(i).getId().equals(animal.getId())) {
                listaAnimais.set(i, animal);
            }
        }
        animalJson.manipularJson(listaAnimais);
        return animal;
    }
}
