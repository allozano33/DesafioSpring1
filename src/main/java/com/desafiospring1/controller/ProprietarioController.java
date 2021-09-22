package com.desafiospring1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/proprietario")
public class ProprietarioController {

    //@PostMapping(value = "/cadastrar")
    //@GetMapping(value = "/obter")
    //@GetMapping(value = "/listar")
    //@GetMapping(value = "/buscar")
    //@GetMapping(value = "/deletar")
//
//    @Autowired
//    private Proprietario proprietario;
//    }
//
//    @GetMapping("/busca_id/{id}")
//    public Proprietario getAlunobyId(@PathVariable("id") Integer id) {
//        Proprietario proprietario = proprietarioService.getProprietario(id);
//        return proprietario;
//    }
//
//    @GetMapping("/listar")
//    public List<Proprietario> listaProprietario(){
//        return Proprietario.listar();
//    }
//
//    @GetMapping("/{nome}")
//    public Proprietario obtemProprietario(@PathVariable String nome){
//        return proprietario.buscarProprietario(nome);
//    }
//
//    @DeleteMapping(value ="/deleta/{id}")
//    public String remover(@PathVariable("id") Integer id) {
//        proprietario.remove(id);
//        return "deletado";
//    }
//
//    @PutMapping(value = "/atualiza")
//    public Aluno atualizar(@RequestBody Proprietario proprietario) {
//        return Proprietario.atualizar(proprietario);
}
