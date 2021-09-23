package com.desafiospring1.controller;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<AnimalDto> cadastraAnimal(@RequestBody AnimalDto animalDto, UriComponentsBuilder uriBuilder) {
        Animal animal = animalDto.converte();
        Animal animalCadastrado = animalService.cadastrar(animal);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(animalCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(AnimalDto.converte(animalCadastrado));
    }

    @GetMapping("/listar")
    public List<Animal> listaAnimal(){
        return animalService.listar();
    }

    @GetMapping("/buscar/{id}")
    public Animal buscaAnimalPorId(@PathVariable("id") Long id){
        Animal animal = animalService.buscaAnimalPorId(id);
        return animal;
    }

    //----------------------------Atenção--------------------------------
    //Falta verificação se existe consulta para o animal antes de deletar
    //O mesmo deve ser feito para Medico e proprietario
    //-------------------------------------------------------------------
    @DeleteMapping(value ="/deletar/{id}")
    public List<Animal> deletaAnimal(@PathVariable("id") Long id) {
        return animalService.deletaAnimal(id);
    }

    @PutMapping(value ="/atualizar")
    public Animal atualizarAnimal(@RequestBody Animal animal) {
        return animalService.atualizaAnimal(animal);
    }
}








