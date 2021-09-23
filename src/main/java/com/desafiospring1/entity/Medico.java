package com.desafiospring1.entity;

import lombok.Data;

import java.text.ParseException;

@Data
public class Medico extends Pessoa{

    private Long id;
    private String numeroDeRegistro;
    private String especialidade;

    public Medico(){

    }

    public Medico(String cpf, String nome, String sobrenome, Long id, String numeroDeRegistro, String especialidade) throws ParseException {
        super(cpf, nome, sobrenome);
        this.id = id;
        this.numeroDeRegistro = numeroDeRegistro;
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "{" +
                " \"id\":" + id +
                ", \"cpf\":\"" + super.getCpf() + "\"" +
                ", \"nome\":\"" + super.getNome() + "\"" +
                ", \"sobrenome\":\"" + super.getSobrenome() + "\"" +
                ", \"numeroDeRegistro\":\"" + numeroDeRegistro + "\"" +
                ", \"especialidade\":\"" + especialidade + "\"" +
                "}";
    }
}
