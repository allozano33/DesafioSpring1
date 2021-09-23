package com.desafiospring1.entity;

import lombok.Data;

import java.text.ParseException;

@Data
public class Medico extends Pessoa{

    private Long id;
    private Long numeroDeRegistro;
    private String especialidade;

    public Medico(){

    }

    public Medico(String cpf, String nome, String sobrenome, Long id, Long numeroDeRegistro, String especialidade) throws ParseException {
        super(cpf, nome, sobrenome);
        this.id = id;
        this.numeroDeRegistro = numeroDeRegistro;
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "{" +
                ", \"cpf\":\"" + super.getCpf() + "\"" +
                ", \"nome\":\"" + super.getNome() + "\"" +
                ", \"sobrenome\":\"" + super.getSobrenome() + "\"" +
                ", \"id\":\"" + id + "\"" +
                ", \"numeroDeRegistro\":\"" + numeroDeRegistro + "\"" +
                ", \"especialidade\":\"" + especialidade + "\"" +
                "}";
    }
}
