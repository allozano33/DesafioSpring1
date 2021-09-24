package com.desafiospring1.dto;

import com.desafiospring1.entity.Proprietario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalDto {

    private Long id;
    private String numeroPaciente;
    private String especie;
    private String raca;
    private String cor;
    private LocalDate dataDeNascimento;
    private String nome;
    private Proprietario proprietario;

    public AnimalDto(){

    }

    public AnimalDto(Long id, String numeroPaciente, String especie, String raca, String cor, LocalDate dataDeNascimento, String nome, Proprietario proprietario) {
        this.id = id;
        this.numeroPaciente = numeroPaciente;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.proprietario = proprietario;
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
                ", \"proprietario\":" + proprietario +
                "}";
    }
}
