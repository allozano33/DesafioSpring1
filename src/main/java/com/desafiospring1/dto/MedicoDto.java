package com.desafiospring1.dto;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Pessoa;
import lombok.Data;

import java.text.ParseException;

@Data
public class MedicoDto extends Pessoa {

    private Long id;
    private Long numeroDeRegistro;
    private String especialidade;

    public MedicoDto() {

    }

    public MedicoDto(String cpf, String nome, String sobrenome, Long id, Long numeroDeRegistro, String especialidade) throws ParseException {
        super(cpf, nome, sobrenome);
        this.id = id;
        this.numeroDeRegistro = numeroDeRegistro;
        this.especialidade = especialidade;
    }

    /**
     * @return animal
     */
    public Medico converte(MedicoDto medicoDto) throws ParseException {
        Medico medico = new Medico(medicoDto.getCpf(),medicoDto.getNome(),medicoDto.getSobrenome(),this.id,this.numeroDeRegistro,this.especialidade);
        return medico;
    }

    /**
     * @param medico
     * @return animalDto
     */
    public static MedicoDto converte(Medico medico) throws ParseException {
        MedicoDto medicoDto = new MedicoDto(medico.getCpf(), medico.getNome(), medico.getSobrenome(), medico.getId(), medico.getNumeroDeRegistro(), medico.getEspecialidade());
        return medicoDto;
    }
}

