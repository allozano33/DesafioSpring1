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
        Animal animalCadastrado = animalService.cadastra(animal);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(animalCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(AnimalDto.converte(animalCadastrado));
    }

//    @GetMapping("/listar")
//    public List<Animal> listaAnimal(){
//        return Animal.listar();
//    }
//
//    @GetMapping("/buscar/{id}")
//    public animal getAnimal {
//        Animal animal = animal.getAnimal(id);
//        return animal;
//    }

//    @DeleteMapping(value ="/deletar/{id}")
//    public String remover(@PathVariable("id") Integer id) {
//        proprietario.remove(id);
//        return "deletado";
//    }
}








