package com.desafiospring1.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Consulta {

    private Long id;
    private String codigo;
    private LocalDateTime dataHora;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private Long idMedico;
    private Long idAnimal;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"codigo\":\"" + codigo + "\"" +
                ", \"dataHora\":\"" + dataHora + "\"" +
                ", \"motivo\":\"" + motivo + "\"" +
                ", \"diagnostico\":\"" + diagnostico + "\"" +
                ", \"tratamento\":\"" + tratamento + "\"" +
                ", \"idMedico\":" + idMedico +
                ", \"idAnimal\":" + idAnimal +
                '}';
    }
}
