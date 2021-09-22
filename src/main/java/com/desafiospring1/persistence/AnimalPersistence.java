package com.desafiospring1.persistence;

import com.desafiospring1.entity.Animal;

import java.util.ArrayList;
import java.util.List;

//classe especializada em realizar persistencia

public class AnimalPersistence {

    private static List<Animal> lista = new ArrayList<>();


    public Animal cadastro(Animal obj) {
        obj.setId(obj.size() + 1L);
        obj.add(obj);
        return obj;
    }

    public List<Animal> listagem() {
        return lista;
    }

    public Animal obtemAnuncio(Long id) {
        for (Animal obj : lista) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }

    public void remove(Long id) {
        for (Animal obj : lista) {
            if (obj.getId().equals(id)) {
                lista.remove(obj);
            }
        }
    }

    public Animal atualizar(Animal obj) {
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i).getId().equals(obj.getId())) {
                obj.set(i, obj);
            }
        }
        return obj;
    }
}
