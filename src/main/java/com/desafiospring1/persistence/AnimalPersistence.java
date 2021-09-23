package com.desafiospring1.persistence;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.util.AnimalJson;

import java.util.ArrayList;
import java.util.List;

public class AnimalPersistence {

    private AnimalJson animalJson = new AnimalJson();
    private static List<AnimalDto> listaAnimais = new ArrayList<>();

    public AnimalDto cadastra(AnimalDto animalDto) {
        animalDto.setId(animalJson.listar().size() + 1L);
        for (AnimalDto animais: animalJson.listar()) {
            listaAnimais.add(animais);
        }
        listaAnimais.add(animalDto);
        animalJson.manipularJson(listaAnimais);
        return animalDto;
    }

    public List<AnimalDto> listagem() {
        return animalJson.listar();
    }

    public AnimalDto buscaAnimalPorId(Long id) {
        for (AnimalDto animalDto : animalJson.listar()) {
            if (animalDto.getId().equals(id)) {
                return animalDto;
            }
        }
        return null;
    }

    public List<AnimalDto> deletaAnimal(Long id) {
        listaAnimais = animalJson.listar();
        for(int i=0; i<listaAnimais.size();i++){
            if(listaAnimais.get(i).getId().equals(id)){
                listaAnimais.remove(i);
            }
        }
        animalJson.manipularJson(listaAnimais);
        return animalJson.listar();
    }

    public AnimalDto atualizaAnimal(AnimalDto animalDto){
        listaAnimais = animalJson.listar();
        for (int i = 0; i < listaAnimais.size(); i++) {
            if (listaAnimais.get(i).getId().equals(animalDto.getId())) {
                listaAnimais.set(i, animalDto);
            }
        }
        animalJson.manipularJson(listaAnimais);
        return animalDto;
    }
}
