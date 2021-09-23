package com.desafiospring1.dto;

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
    private Long idProprietario;

    public AnimalDto(){

    }

    public AnimalDto(Long id, String numeroPaciente, String especie, String raca, String cor, LocalDate dataDeNascimento, String nome, Long idProprietario) {
        this.id = id;
        this.numeroPaciente = numeroPaciente;
        this.especie = especie;
        this.raca = raca;
        this.cor = cor;
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.idProprietario = idProprietario;
    }
}
