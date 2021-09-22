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

    public Animal(String numeroPaciente, String especie, String raca, String cor, LocalDate dataDeNascimento, String nome) {
        this.numeroPaciente = numeroPaciente;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
    }
}
