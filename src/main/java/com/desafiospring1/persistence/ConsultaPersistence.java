package com.desafiospring1.persistence;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.util.ConsultaJson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaPersistence {

    private ConsultaJson consultaJson = new ConsultaJson();
    private static List<Consulta> listaConsultas = new ArrayList<>();

    public Consulta cadastra(Consulta consulta) throws IOException {
        consulta.setId(consultaJson.listar().size() + 1L);
        List<Consulta> novaListaConsultas = consultaJson.listar();
        novaListaConsultas.add(consulta);
        consultaJson.manipularJson(novaListaConsultas);
        return consulta;
    }

    public List<Consulta> listagem() {
        return consultaJson.listar();
    }

    public List<ConsultaDto> listagemCompleta() {
        return consultaJson.listarDadosCompletos();
    }

    public List<Consulta> buscaConsultaPorId() {
        return consultaJson.listar();
    }

    public Consulta atualizaConsulta(Consulta consulta) throws IOException {
        listaConsultas = consultaJson.listar();
        for (int i = 0; i < listaConsultas.size(); i++) {
            if (listaConsultas.get(i).getId().equals(consulta.getId())) {
                listaConsultas.set(i, consulta);
            }
        }
        consultaJson.manipularJson(listaConsultas);
        return consulta;
    }
}
