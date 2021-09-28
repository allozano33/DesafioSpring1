package com.desafiospring1.controller;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Animal> cadastraAnimal(@RequestBody Animal animal, UriComponentsBuilder uriBuilder) throws IOException {
        Animal animalCadastrado = animalService.cadastrar(animal);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(animalCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(animalCadastrado);
    }

    @GetMapping("/listar")
    public List<Animal> listaAnimal(){
        return animalService.listar();
    }

    @GetMapping("/listarDadosCompletos")
    public List<AnimalDto> listaAnimalDadosCompletos(){
        return animalService.listarDadosCompletos();
    }

    @GetMapping("/buscar/{id}")
    public Animal buscaAnimalPorId(@PathVariable("id") Long id){
        return animalService.buscaAnimalPorId(id);
    }

    @DeleteMapping(value ="/deletar/{id}")
    public List<Animal> deletaAnimal(@PathVariable("id") Long id) throws IOException {
        return animalService.deletaAnimal(id);
    }

    @PutMapping(value ="/atualizar")
    public Animal atualizarAnimal(@RequestBody Animal animal) throws IOException {
        return animalService.atualizaAnimal(animal);
    }
}








