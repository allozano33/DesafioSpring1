package com.desafiospring1.persistence;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.util.ConsultaJson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ConsultaDto> listagemAnimalPorData(Long id) {
        List<ConsultaDto> novaListaConsultas = consultaJson.listarDadosCompletos();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .filter(item -> item.getAnimalDto().getId().equals(id))
                .sorted((ConsultaDto a, ConsultaDto b) -> b.getDataHora().compareTo(a.getDataHora()))
                .collect(Collectors.toList());

        return consultas;
    }

    public List<ConsultaDto> listagemPorNomeProprietario() {
        List<ConsultaDto> novaListaConsultas = consultaJson.listarDadosCompletos();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .sorted((ConsultaDto a, ConsultaDto b) -> a.getAnimalDto().getProprietario().getNome().compareTo(b.getAnimalDto().getProprietario().getNome()))
                .collect(Collectors.toList());

        return consultas;
    }

    public List<ConsultaTotalMedicoDto> listarTotalDeConsultaPorMedico() {
        List<ConsultaDto> totalConsultas = consultaJson.listarDadosCompletos();
        List<ConsultaTotalMedicoDto> consultaTotalMedico = new ArrayList<>();

        for(int i = 0; i < totalConsultas.size(); i++){
            ConsultaDto consulta_i = totalConsultas.get(i);
            int count = 0;

            for(int j = 0; j < totalConsultas.size(); j++){
                ConsultaDto consulta_j = totalConsultas.get(j);
                if(consulta_i.getMedico().getId().equals(consulta_j.getMedico().getId())){
                    count++;
                }
            }

            totalConsultas.remove(i);

            ConsultaTotalMedicoDto consultaTotalMedicoDto = new ConsultaTotalMedicoDto();
            consultaTotalMedicoDto.setNomeMedico(consulta_i.getMedico().getNome());
            consultaTotalMedicoDto.setTotal(count);

            consultaTotalMedico.add(consultaTotalMedicoDto);
        }

        return consultaTotalMedico;
    }

    public List<ConsultaDto> listagemConsultaPorDia(String data) {

        String[] dataFormatada = data.split("-");

        int ano = Integer.parseInt(dataFormatada[2]);
        int mes = Integer.parseInt(dataFormatada[1]);
        int dia = Integer.parseInt(dataFormatada[0]);

        LocalDate dataConvertida = LocalDate.of(ano,mes,dia);

        List<ConsultaDto> novaListaConsultas = consultaJson.listarDadosCompletos();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .filter(item -> item.getDataHora().toLocalDate().equals(dataConvertida))
                .sorted((ConsultaDto a, ConsultaDto b) -> a.getDataHora().compareTo(b.getDataHora()))
                .collect(Collectors.toList());

        return consultas;
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
