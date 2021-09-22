package com.desafiospring1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/medico")
public class MedicoController {


    //@PostMapping(value = "/cadastrar")
    //@GetMapping(value = "/obter")
    // @GetMapping(value = "/listar")
    //@GetMapping(value = "/buscar")
    //@GetMapping(value = "/deletar")

//    @Autowired
//    private Medico medico;
//    }
//    @GetMapping("/listar")
//    public List<Medico> listaMedico(){
//        return medico.listar();
//    }
//
//    @GetMapping("/buscar")
//    public medico getMedico{
//        Medico medico  = Medico.getMedico();
//        return medico;
//    }
//
//    @GetMapping("/{nome}")
//    public Medico obtemAnimal(@PathVariable String nome){
//        return medico.buscarMedico(nome);
//    }
//
//    @DeleteMapping(value ="/deletar/{medico}")
//    public String remover(@PathVariable("medico") Integer animal) {
//        medico.remove(id);
//        return "deletado";
//    }
//
//    @PutMapping(value = "/cadastrar")
//    public Medico atualizar(@RequestBody Medico medico) {
//        return Medico.atualizar(medico);
}