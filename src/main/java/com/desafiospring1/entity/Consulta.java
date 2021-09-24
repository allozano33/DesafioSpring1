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

    public Consulta() {

    }

    public Consulta(Long id, String codigo, LocalDateTime dataHora, String motivo, String diagnostico, String tratamento, Long idMedico, Long idAnimal) {
        this.id = id;
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.idMedico = idMedico;
        this.idAnimal = idAnimal;
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
                ", medico=" + idMedico +
                ", animal=" + idAnimal +
                '}';
    }
}
