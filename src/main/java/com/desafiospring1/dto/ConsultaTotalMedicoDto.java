package com.desafiospring1.dto;

import lombok.Data;

@Data
public class ConsultaTotalMedicoDto {

    private Integer total;
    private String nomeMedico;

    public ConsultaTotalMedicoDto() {

    }

    public ConsultaTotalMedicoDto(Integer total, String nomeMedico) {
        this.total = total;
        this.nomeMedico = nomeMedico;
    }

    @Override
    public String toString() {
        return "{" +
                " \"total\":" + total +
                ",\"nomeMedico\":\"" + nomeMedico + "\"" +
                '}';
    }
}
