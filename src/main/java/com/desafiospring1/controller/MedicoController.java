package com.desafiospring1.controller;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Medico> cadastraMedico(@RequestBody Medico medico, UriComponentsBuilder uriBuilder) throws IOException {
        Medico medicoCadastrado = medicoService.cadastrar(medico);

        URI uri = uriBuilder.path("/medico/buscar/{id}").buildAndExpand(medicoCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(medicoCadastrado);
    }

    @GetMapping("/listar")
    public List<Medico> listaMedico(){
        return medicoService.listar();
    }

    @GetMapping("/buscar/{id}")
    public Medico buscaMedicoPorId(@PathVariable("id") Long id){
        return medicoService.buscaMedicoPorId(id);
    }

    @DeleteMapping(value ="/deletar/{id}")
    public List<Medico> deletaMedico(@PathVariable("id") Long id) throws IOException {
        return medicoService.deletaMedico(id);
    }

    @PutMapping(value ="/atualizar")
    public Medico atualizarMedico(@RequestBody Medico medico) throws IOException {
        return medicoService.atualizaMedico(medico);
    }
}








