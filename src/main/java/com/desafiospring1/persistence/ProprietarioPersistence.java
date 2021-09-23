package com.desafiospring1.persistence;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.util.ProprietarioJson;

import java.util.ArrayList;
import java.util.List;


public class ProprietarioPersistence {

    private ProprietarioJson proprietarioJson = new ProprietarioJson();
    private static List<Proprietario> listaProprietario = new ArrayList<>();

    public Proprietario cadastra(Animal animal) {
        animal.setId(proprietarioJson.listar().size() + 1L);
        for (Proprietario proprietario: proprietarioJson.listar()) {
            listaProprietario.add(proprietario);
        }
        listaProprietario.add(proprietario);
        proprietarioJson.manipularJson(listaProprietario);
        return proprietario;
    }

    public List<Animal> listagem() {
        return (List<Animal>) proprietarioJson.listar();
    }

    public Animal buscaAnimalPorId(Long id) {
        for (Animal animal : proprietarioJson.listar()) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    public List<Animal> deletaAnimal(Long id) {
        listaProprietario = (List<Proprietario>) proprietarioJson.listar();
        for(int i=0; i<listaProprietario.size();i++){
            if(listaProprietario.get(i).getId().equals(id)){
                listaProprietario.remove(i);
            }
        }
       proprietarioJson.manipularJson(listaProprietario);
        return (List<Animal>) proprietarioJson.listar();
    }

    public Proprietario atualizaProprietario(Proprietario proprietario){
        listaProprietario = (List<Proprietario>) proprietarioJson.listar();
        for (int i = 0; i < listaProprietario.size(); i++) {
            if (listaProprietario.get(i).getId().equals(proprietario.getId())) {
                listaProprietario.set(i, proprietario);
            }
        }
        proprietarioJson.manipularJson(listaProprietario);
        return proprietario;
    }
}
