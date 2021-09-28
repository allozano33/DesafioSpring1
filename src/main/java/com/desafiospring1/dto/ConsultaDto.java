package com.desafiospring1.dto;

import com.desafiospring1.entity.Medico;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaDto {

    private Long id;
    private String codigo;
    private LocalDateTime dataHora;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private Medico medico;
    private AnimalDto animalDto;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"codigo\":\"" + codigo + "\"" +
                ", \"dataHora\":\"" + dataHora + "\"" +
                ", \"motivo\":\"" + motivo + "\"" +
                ", \"diagnostico\":\"" + diagnostico + "\"" +
                ", \"tratamento\":\"" + tratamento + "\"" +
                ", \"medico\":" + medico +
                ", \"animal\":" + animalDto +
                '}';
    }
}
