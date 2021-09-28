package com.desafiospring1.controller;

import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/proprietario")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Proprietario> cadastraProprietario (@RequestBody Proprietario proprietario, UriComponentsBuilder uriBuilder) throws IOException {
        Proprietario proprietarioCadastrado = proprietarioService.cadastrar(proprietario);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(proprietarioCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(proprietarioCadastrado);
    }

    @GetMapping("/listar")
    public List<Proprietario> listaAnimal ( ) {
        return proprietarioService.listar();
    }

    @GetMapping("/buscar/{id}")
    public Proprietario buscaProprietarioPorId (@PathVariable("id") Long id) {
        return proprietarioService.buscaProprietarioPorId(id);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public List<Proprietario> deletaProprietario (@PathVariable("id") Long id) throws IOException {
        return proprietarioService.deletaProprietario(id);
    }

    @PutMapping(value = "/atualizar")
    public Proprietario atualizarProprietario (@RequestBody Proprietario proprietario) throws IOException {
        return proprietarioService.atualizaProprietario(proprietario);
    }
}