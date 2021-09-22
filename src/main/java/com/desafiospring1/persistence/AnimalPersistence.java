package com.desafiospring1.persistence;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.util.AnimalJson;

import java.util.ArrayList;
import java.util.List;

public class AnimalPersistence {

    private AnimalJson animalJson = new AnimalJson();
    private static List<Animal> listaAnimais = new ArrayList<>();

    public Animal cadastra(Animal animal) {
        animal.setId(listaAnimais.size() + 1L);
        listaAnimais.add(animal);
        animalJson.cadastrar(listaAnimais);
        return animal;
    }

    public List<Animal> listagem() {

        return listaAnimais;
    }


//
//    public Animal obtemAnuncio(Long id) {
//        for (Animal obj : lista) {
//            if (obj.getId().equals(id)) {
//                return obj;
//            }
//        }
//        return null;
//    }
//
//    public void remove(Long id) {
//        for (Animal obj : lista) {
//            if (obj.getId().equals(id)) {
//                lista.remove(obj);
//            }
//        }
//    }
//
//    public Animal atualizar(Animal obj) {
//        for (int i = 0; i < obj.size(); i++) {
//            if (obj.get(i).getId().equals(obj.getId())) {
//                obj.set(i, obj);
//            }
//        }
//        return obj;
//    }
}
