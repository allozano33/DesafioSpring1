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

    public Animal(){

    }

    public Animal(Long id, String numeroPaciente, String especie, String raca, String cor, LocalDate dataDeNascimento, String nome, Long idProprietario) {
        this.id = id;
        this.numeroPaciente = numeroPaciente;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.idProprietario = idProprietario;
    }

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
