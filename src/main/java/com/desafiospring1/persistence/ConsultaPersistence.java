package com.desafiospring1.persistence;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.util.ConsultaJson;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPersistence {

    private ConsultaJson consultaJson = new ConsultaJson();
    private static List<Consulta> listaConsultas = new ArrayList<>();

    public Consulta cadastra(Consulta consulta) {
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

    public Consulta buscaConsultaPorId(Long id) {
        for (Consulta consulta : consultaJson.listar()) {
            if (consulta.getId().equals(id)) {
                return consulta;
            }
        }
        return null;
    }

    public List<Consulta> deletaConsulta(Long id) {
        listaConsultas = consultaJson.listar();
        for (int i = 0; i < listaConsultas.size(); i++) {
            if (listaConsultas.get(i).getId().equals(id)) {
                listaConsultas.remove(i);
            }
        }
        consultaJson.manipularJson(listaConsultas);
        return consultaJson.listar();
    }

    public Consulta atualizaConsulta(Consulta consulta) {
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
