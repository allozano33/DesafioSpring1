package com.desafiospring1.controller;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
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
    public ResponseEntity<Consulta> cadastraConsulta(@RequestBody Consulta consulta, UriComponentsBuilder uriBuilder) {
        Consulta consultaCadastrado = consultaService.cadastrar(consulta);

        URI uri = uriBuilder.path("/animal/buscar/{id}").buildAndExpand(consultaCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(consultaCadastrado);
    }

    @GetMapping("/listar")
    public List<Consulta> listaConsulta() {
        return consultaService.listar();
    }

    @GetMapping("/listarDadosCompletos")
    public List<ConsultaDto> listaConsultaDadosCompletos() {
        return consultaService.listarDadosCompletos();
    }

    @GetMapping("/listarAnimalPorData/{id}")
    public List<ConsultaDto> listaAnimalPorData(@PathVariable("id") Long id) {
        return consultaService.listarAnimalPorData(id);
    }

    @GetMapping("/listarPorNomeProprietario")
    public List<ConsultaDto> listaPorNomeProprietario() {
        return consultaService.listarPorNomeProprietario();
    }

    @GetMapping("/listarTotalDeConsultaPorMedico")
    public List<ConsultaTotalMedicoDto> listaTotalDeConsultaPorMedico() {
        return consultaService.listarTotalDeConsultaPorMedico();
    }

    @GetMapping("/listarConsultasDoDia/{data}")
    public List<ConsultaDto> listaConsultasDoDia(@PathVariable("data") String data) {
        return consultaService.listarConsultasDoDia(data);
    }

    @GetMapping("/buscar/{id}")
    public Consulta buscaAnimalPorId(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.buscaConsultaPorId(id);
        return consulta;
    }

//    @DeleteMapping(value = "/deletar/{id}")
//    public List<Consulta> deletaConsulta(@PathVariable("id") Long id) {
//        return consultaService.deletaConsulta(id);
//    }

    @PutMapping(value = "/atualizar")
    public Consulta atualizarAnimal(@RequestBody Consulta consulta) {
        return consultaService.atualizaConsulta(consulta);
    }
}








