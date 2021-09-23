package com.desafiospring1.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Consulta {

    private Long id;
    private String codigo;
    private LocalDateTime dataHora;
    private String motivo;
    private String diagnostico;
    private String tratamento;
    private Medico medico;
    private Animal animal;

    public Consulta() {

    }

    public Consulta(Long id, String codigo, LocalDateTime dataHora, String motivo, String diagnostico, String tratamento, Medico medico, Animal animal) {
        this.id = id;
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.medico = medico;
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", dataHora=" + dataHora +
                ", motivo='" + motivo + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamento='" + tratamento + '\'' +
                ", medico=" + medico +
                ", animal=" + animal +
                '}';
    }
}
