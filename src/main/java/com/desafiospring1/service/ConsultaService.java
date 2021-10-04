package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private ConsultaPersistence persistence = new ConsultaPersistence();

    public ConsultaService() {}

    public ConsultaService(ConsultaPersistence persistence) {
        this.persistence = persistence;
    }

    private boolean codigoNaoUtilizado(String codigo) {
        for (Consulta consulta : persistence.listagem()) {
            if (consulta.getCodigo().equals(codigo)) {
                return false;
            }
        }
        return true;
    }

    public Consulta cadastrar(Consulta consulta) throws IOException {
        if (codigoNaoUtilizado(consulta.getCodigo())) {
            consulta.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(consulta);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<Consulta> listar() {
        return persistence.listagem();
    }

    public List<ConsultaDto> listarAnimalPorData(Long id) {
        List<ConsultaDto> novaListaConsultas = persistence.listagemCompleta();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .filter(item -> item.getAnimalDto().getId().equals(id))
                .sorted((ConsultaDto a, ConsultaDto b) -> b.getDataHora().compareTo(a.getDataHora()))
                .collect(Collectors.toList());

        return consultas;
    }

    public List<ConsultaDto> listarPorNomeProprietario() {
        List<ConsultaDto> novaListaConsultas = persistence.listagemCompleta();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .sorted((ConsultaDto a, ConsultaDto b) -> a.getAnimalDto().getProprietario().getNome().compareTo(b.getAnimalDto().getProprietario().getNome()))
                .collect(Collectors.toList());
        return consultas;
    }

    public List<ConsultaTotalMedicoDto> listarTotalDeConsultaPorMedico() {
        List<ConsultaDto> totalConsultas = persistence.listagemCompleta();
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

    public List<ConsultaDto> listarConsultasDoDia(String data) {
        List<ConsultaDto> novaListaConsultas = persistence.listagemCompleta();

        String[] dataFormatada = data.split("-");

        int ano = Integer.parseInt(dataFormatada[2]);
        int mes = Integer.parseInt(dataFormatada[1]);
        int dia = Integer.parseInt(dataFormatada[0]);

        LocalDate dataConvertida = LocalDate.of(ano,mes,dia);

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .filter(item -> item.getDataHora().toLocalDate().equals(dataConvertida))
                .sorted((ConsultaDto a, ConsultaDto b) -> a.getDataHora().compareTo(b.getDataHora()))
                .collect(Collectors.toList());

        return consultas;
    }

    public List<ConsultaDto> listarDadosCompletos() {
        return persistence.listagemCompleta();
    }

    public Consulta buscaConsultaPorId(Long id) {
        List<Consulta> consultas = persistence.buscaConsultaPorId();

        for (Consulta consulta : consultas) {
            if (consulta.getId().equals(id)) {
                return consulta;
            }
        }
        return null;
    }

    public Consulta atualizaConsulta(Consulta consulta) throws IOException {
        return persistence.atualizaConsulta(consulta);
    }
}
