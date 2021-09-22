package com.desafiospring1.dto;

import com.desafiospring1.entity.Animal;
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


    /**
     * @return animal
     */
    public Animal converte() {
        Animal animal = new Animal(this.id, this.numeroPaciente, this.especie, this.raca, this.cor, this.dataDeNascimento, this.nome, this.proprietario);
        return animal;
    }

    /**
     * @param animal
     * @return animalDto
     */
    public static AnimalDto converte(Animal animal) {
        AnimalDto animalDto = new AnimalDto(animal.getId(), animal.getNumeroPaciente(), animal.getEspecie(), animal.getRaca(), animal.getCor(), animal.getDataDeNascimento(), animal.getNome(), animal.getProprietario());
        return animalDto;
    }

}
