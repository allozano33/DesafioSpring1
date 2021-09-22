package com.desafiospring1.entity;

import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDate;

@Setter
public class Proprietario extends Pessoa{

    private Long id;
    private LocalDate dataDeNascimento;
    private String endereco;
    private String telefoneContato;

    public Proprietario(){

    }

    public Proprietario(String cpf, String nome, String sobrenome, Long id, LocalDate dataDeNascimento, String endereco, String telefoneContato) throws ParseException {
        super(cpf, nome, sobrenome);
        this.id = id;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.telefoneContato = telefoneContato;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cpf\":\"" + super.getCpf() + "\"" +
                ", \"nome\":\"" + super.getNome() + "\"" +
                ", \"sobrenome\":\"" + super.getSobrenome() + "\"" +
                ", \"dataDeNascimento\":\"" + dataDeNascimento + "\"" +
                ", \"endereco\":\"" + endereco + "\"" +
                ", \"telefoneContato\":\"" + telefoneContato + "\"" +
                "}";
    }
}
