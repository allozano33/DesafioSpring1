package com.desafiospring1.dto;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Proprietario;
import lombok.Data;

import java.time.LocalDate;
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
    private Animal animal;

    public ConsultaDto() {

    }

    public ConsultaDto(Long id, String codigo, LocalDateTime dataHora, String motivo, String diagnostico, String tratamento, Medico medico, Animal animal) {
        this.id = id;
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.medico = medico;
        this.animal = animal;
    }

    /**
     * @return consulta
     */
    public Consulta converte() {
        Consulta consulta = new Consulta(this.id, this.codigo, this.dataHora, this.motivo, this.diagnostico, this.tratamento, this.medico, this.animal);
        return consulta;
    }

    /**
     * @param consulta
     * @return consultaDto
     */
    public static ConsultaDto converte(Consulta consulta) {
        ConsultaDto consultaDto = new ConsultaDto(consulta.getId(), consulta.getCodigo(), consulta.getDataHora(), consulta.getMotivo(), consulta.getDiagnostico(), consulta.getTratamento(), consulta.getMedico(), consulta.getAnimal());
        return consultaDto;
    }

}