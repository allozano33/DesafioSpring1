package com.desafiospring1.dto;

import com.desafiospring1.entity.Animal;
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

    public ConsultaDto() {

    }

    public ConsultaDto(Long id, String codigo, LocalDateTime dataHora, String motivo, String diagnostico, String tratamento, Medico medico, AnimalDto animalDto) {
        this.id = id;
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.medico = medico;
        this.animalDto = animalDto;
    }

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
