package com.desafiospring1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consulta")
public class ConsultaController {


    //@PostMapping(value = "/cadastrar")
    //@GetMapping(value = "/obter")
    //@GetMapping(value = "/listar")
    //@GetMapping(value = "/buscar")
    //@GetMapping(value = "/deletar")

//    @GetMapping("/listar")
//    public List<Consulta> listaConsulta(){
//        return consulta.listar();
//    }

//    @GetMapping("/buscar")
//    public Consulta getConsulta() {
//        Consulta consulta = consulta.getConsulta();
//        return consulta;

//    @GetMapping("/{nome}")
//    public Consulta obtemConsulta(@PathVariable String nome){
//        return Consulta.buscarConsulta(nome);
//    }
//
//    @DeleteMapping(value ="/deletar/{id}")
//    public String remover(@PathVariable("id") Integer id) {
//        Consulta.remove(id);
//        return "deletado";
//    }
//
//    @PostMapping(value = "/cadastrar")
//    public Consulta atualizar(@RequestBody Consulta consulta) {
//        return Consulta.atualizar(consulta);
}
