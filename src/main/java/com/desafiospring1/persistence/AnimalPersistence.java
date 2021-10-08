package com.desafiospring1.persistence;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.util.AnimalJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalPersistence {

    private AnimalJson animalJson = new AnimalJson();
    private static List<Animal> listaAnimais = new ArrayList<>();

    /**
     *
     * @param animal é esperado um objeto do tipo animal
     * @return - retorna animal cadastrado no arquivo animal.json
     * @throws IOException - lança uma exceção caso ocorra erro no cadastro de animal
     * @author Grupo 5 - Tester Alessandro
     */
    public Animal cadastra(Animal animal) throws IOException {
        animal.setId(animalJson.listar().size() + 1L);
        List<Animal> novaListaAnimais = animalJson.listar();
        novaListaAnimais.add(animal);
        animalJson.manipularJson(novaListaAnimais);
        return animal;
    }

    /**
     *
     * @return retona a lista de animais do arquivo animal.json
     * @author Grupo 5 - Tester Alessandro
     */
    public List<Animal> listagem() {
        return animalJson.listar();
    }

    /**
     *
     * @return - lista de animais do tipo AnimalDto com todos os dados de proprietário
     * @author Grupo 5 - Tester Alessandro
     */
    public List<AnimalDto> listagemCompleta() {
        return animalJson.listarDadosCompletos();
    }

    /**
     *
     * @param id - é esperado parametro id do animal
     * @return - retorna a lista de animal apos a deleção do arquivo animal.json
     * @throws IOException - lanca execeção caso ocorra erro na deleçào dos animais
     * @author Grupo 5 - Tester Alessandro
     */
    public List<Animal> deletaAnimal(Long id) throws IOException {
        listaAnimais = animalJson.listar();
        for(int i=0; i<listaAnimais.size();i++){
            if(listaAnimais.get(i).getId().equals(id)){
                listaAnimais.remove(i);
            }
        }
        animalJson.manipularJson(listaAnimais);
        return animalJson.listar();
    }

    /**
     *
     * @param animal - é esperado um objeto do tipo animal
     * @return - retorna o animal após a atualização do arquivo animal
     * @throws IOException - lança execeção caso ocorra na atualização de animal
     * @author Grupo 5 - Tester Alessandro
     */
    public Animal atualizaAnimal(Animal animal) throws IOException {
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
