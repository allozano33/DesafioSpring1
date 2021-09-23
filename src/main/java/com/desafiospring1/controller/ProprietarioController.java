package com.desafiospring1.controller;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;



    @RestController
    @RequestMapping(value = "/proprietario")

    public class ProprietarioController {

        @Autowired
        private ProprietarioService proprietarioService;

        @PostMapping(value = "/cadastrar")
        public ResponseEntity<ProprietarioDto> cadastraProprietario (@RequestBody ProprietarioDto proprietarioDto, UriComponentsBuilder uriBuilder) {
            Proprietario proprietario = proprietarioDto.converte();
            Proprietario proprietarioCadastrado = proprietarioService.cadastrar(proprietario);

            URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(proprietarioCadastrado.getId()).toUri();
            return ResponseEntity.created(uri).body(ProprietarioDto.converte(proprietarioCadastrado));
        }

        @GetMapping("/listar")
        public List<Animal> listaAnimal ( ) {
            return proprietarioService.listar();
        }

        @GetMapping("/buscar/{id}")
        public Animal buscaAnimalPorId (@PathVariable("id") Long id) {
            Animal propriedade = proprietarioService.buscaPropriedadePorId(id);
            return propriedade;
        }

        //----------------------------Atenção--------------------------------
        //Falta verificação se existe consulta para o animal antes de deletar
        //O mesmo deve ser feito para Medico e proprietario
        //-------------------------------------------------------------------
        @DeleteMapping(value = "/deletar/{id}")
        public List<Animal> deletaAnimal (@PathVariable("id") Long id) {
            return proprietarioService.deletaProprietario(id);
        }

        @PutMapping(value = "/atualizar")
        public Animal atualizarAnimal (@RequestBody Proprietario proprietario) {
            return proprietarioService.atualizaProprietario(proprietario);
        }
    }
}