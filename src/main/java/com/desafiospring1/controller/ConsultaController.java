package com.desafiospring1.controller;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.service.AnimalService;
import com.desafiospring1.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ConsultaDto> cadastraConsulta(@RequestBody ConsultaDto consultaDto, UriComponentsBuilder uriBuilder) {
        Consulta consulta = consultaDto.converte();
        Consulta consultaCadastrado = consultaService.cadastrar(consulta);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(consultaCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(ConsultaDto.converte(consultaCadastrado));
    }

    @GetMapping("/listar")
    public List<Consulta> listaConsulta() {
        return consultaService.listar();
    }

    @GetMapping("/buscar/{id}")
    public Consulta buscaAnimalPorId(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.buscaConsultaPorId(id);
        return consulta;
    }

    //----------------------------Atenção--------------------------------
    //Falta verificação se existe consulta para o animal antes de deletar
    //O mesmo deve ser feito para Medico e proprietario
    //-------------------------------------------------------------------
    @DeleteMapping(value = "/deletar/{id}")
    public List<Consulta> deletaConsulta(@PathVariable("id") Long id) {
        return consultaService.deletaConsulta(id);
    }

    @PutMapping(value = "/atualizar")
    public Consulta atualizarAnimal(@RequestBody Consulta consulta) {
        return consultaService.atualizaConsulta(consulta);
    }
}








