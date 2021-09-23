package com.desafiospring1.controller;

import com.desafiospring1.dto.AnimalDto;
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
        AnimalDto animalCadastrado = animalService.cadastrar(animalDto);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(animalCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(animalCadastrado);
    }

    @GetMapping("/listar")
    public List<AnimalDto> listaAnimal(){
        return animalService.listar();
    }

    @GetMapping("/buscar/{id}")
    public AnimalDto buscaAnimalPorId(@PathVariable("id") Long id){
        AnimalDto animalDto = animalService.buscaAnimalPorId(id);
        return animalDto;
    }

    //----------------------------Atenção--------------------------------
    //Falta verificação se existe consulta para o animal antes de deletar
    //O mesmo deve ser feito para Medico e proprietario
    //-------------------------------------------------------------------
    @DeleteMapping(value ="/deletar/{id}")
    public List<AnimalDto> deletaAnimal(@PathVariable("id") Long id) {
        return animalService.deletaAnimal(id);
    }

    @PutMapping(value ="/atualizar")
    public AnimalDto atualizarAnimal(@RequestBody AnimalDto animalDto) {
        return animalService.atualizaAnimal(animalDto);
    }
}








