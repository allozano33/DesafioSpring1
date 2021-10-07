package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaPersistence persistence;

    public ConsultaService() {}

    /**
     *
     * @param persistence - é esperado um parâmetro do tipo ConsultaPersistence para injeção de dependêcia
     */
    public ConsultaService(ConsultaPersistence persistence) {
        this.persistence = persistence;
    }

    /**
     *
     * @param codigo - é esperado um código de uma consulta
     * @return Verificação de duplicidade de código
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    private boolean codigoNaoUtilizado(String codigo) {
        for (Consulta consulta : persistence.listagem()) {
            if (consulta.getCodigo().equals(codigo)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param consulta  - é esperado um objeto do tipo Consulta
     * @return Consulta cadastrada
     * @throws IOException - Lança exceção caso ocorra erro no cadastro de consulta
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public Consulta cadastrar(Consulta consulta) throws IOException {
        if (codigoNaoUtilizado(consulta.getCodigo())) {
            consulta.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(consulta);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    /**
     *
     * @return List de Consultas do tipo Consulta
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public List<Consulta> listar() {
        return persistence.listagem();
    }

    /**
     *
     * @param id - é esperado um Id (LONG) de um Animal
     * @return List de Consultas do tipo ConsultaDTO do id do animal passado como parâmetro em ordem decrescente por DATA da consulta
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public List<ConsultaDto> listarAnimalPorData(Long id) {
        List<ConsultaDto> novaListaConsultas = persistence.listagemCompleta();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .filter(item -> item.getAnimalDto().getId().equals(id))
                .sorted((ConsultaDto a, ConsultaDto b) -> b.getDataHora().compareTo(a.getDataHora()))
                .collect(Collectors.toList());

        return consultas;
    }

    /**
     *
     * @return List de Consultas do tipo ConsultaDTO em ordem crecente por nome do proprietário
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public List<ConsultaDto> listarPorNomeProprietario() {
        List<ConsultaDto> novaListaConsultas = persistence.listagemCompleta();

        List<ConsultaDto> consultas = novaListaConsultas.stream()
                .sorted((ConsultaDto a, ConsultaDto b) -> a.getAnimalDto().getProprietario().getNome().compareTo(b.getAnimalDto().getProprietario().getNome()))
                .collect(Collectors.toList());
        return consultas;
    }

    /**
     *
     * @return List com o Total de consultas do tipo ConsultaTotalMedicoDto para cada médico cadastrado
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
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

    /**
     *
     * @param data é esperado um data na qual será buscada as consultas do dia
     * @return List de Consultas do tipo ConsultaDto da data passada como parâmetro
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
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

    /**
     *
     * @return List de consultas do tipo ConsultaDto
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public List<ConsultaDto> listarDadosCompletos() {
        return persistence.listagemCompleta();
    }

    /**
     *
     * @param id - é esperado um Id (LONG) de uma Consulta
     * @return Consulta para o id passado como parâmetro
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public Consulta buscaConsultaPorId(Long id) {
        List<Consulta> consultas = persistence.buscaConsultaPorId();

        for (Consulta consulta : consultas) {
            if (consulta.getId().equals(id)) {
                return consulta;
            }
        }
        return null;
    }

    /**
     *
     * @param consulta é esperado um objeto do tipo Consulta
     * @return Consulta os seus dados alterados
     * @throws IOException - Lança exceção caso ocorra erro ao atualizar consulta
     * @author Grupo 5 - Test Ana Carolina e Wagner
     */
    public Consulta atualizaConsulta(Consulta consulta) throws IOException {
        return persistence.atualizaConsulta(consulta);
    }
}
