package com.desafiospring1.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Animal {

    private Long id;
    private String numeroPaciente;
    private String especie;
    private String raca;
    private String cor;
    private LocalDate dataDeNascimento;
    private String nome;
    private Long idProprietario;

    @Override
    public String toString() {
        return "{" +
                " \"id\":" + id +
                ", \"numeroPaciente\":\"" + numeroPaciente + "\"" +
                ", \"especie\":\"" + especie + "\"" +
                ", \"raca\":\"" + raca + "\"" +
                ", \"cor\":\"" + cor + "\"" +
                ", \"dataDeNascimento\":\"" + dataDeNascimento + "\"" +
                ", \"nome\":\"" + nome + "\"" +
                ", \"idProprietario\":" + idProprietario +
                "}";
    }
}
