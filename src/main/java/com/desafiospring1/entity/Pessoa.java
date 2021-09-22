package com.desafiospring1.entity;

import lombok.Data;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Data
public class Pessoa {
    private String cpf;
    private String nome;
    private String sobrenome;

    public Pessoa() {

    }

    public Pessoa(String cpf, String nome, String sobrenome) throws ParseException {
        this.cpf = validaCpf(cpf);
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String validaCpf(String cpf) throws ParseException {
        MaskFormatter mf = new MaskFormatter("###.###.###-##");
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(cpf);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                '}';
    }
}
