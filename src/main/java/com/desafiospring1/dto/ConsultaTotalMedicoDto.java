package com.desafiospring1.dto;

import lombok.Data;

@Data
public class ConsultaTotalMedicoDto {

    private Integer total;
    private String nomeMedico;

    @Override
    public String toString() {
        return "{" +
                ", \"total\":" + total +
                ", \"nomeMedico\":\"" + nomeMedico + "\"" +
                '}';
    }
}
