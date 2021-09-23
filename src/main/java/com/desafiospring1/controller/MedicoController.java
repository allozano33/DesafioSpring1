package com.desafiospring1.controller;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Medico> cadastraMedico(@RequestBody Medico medico, UriComponentsBuilder uriBuilder) throws ParseException {
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
        Medico medico = medicoService.buscaMedicoPorId(id);
        return medico;
    }

    //----------------------------Atenção--------------------------------
    //Falta verificação se existe consulta para o animal antes de deletar
    //O mesmo deve ser feito para Medico e proprietario
    //-------------------------------------------------------------------
    @DeleteMapping(value ="/deletar/{id}")
    public List<Medico> deletaMedico(@PathVariable("id") Long id) {
        return medicoService.deletaMedico(id);
    }

    @PutMapping(value ="/atualizar")
    public Medico atualizarMedico(@RequestBody Medico medico) {
        return medicoService.atualizaMedico(medico);
    }
}








