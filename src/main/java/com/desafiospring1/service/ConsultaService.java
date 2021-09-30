package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.dto.ConsultaTotalMedicoDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ConsultaService {

    private ConsultaPersistence persistence;

    public ConsultaService() {

    }

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
        return persistence.listagemAnimalPorData(id);
    }

    public List<ConsultaDto> listarPorNomeProprietario() {
        return persistence.listagemPorNomeProprietario();
    }

    public List<ConsultaTotalMedicoDto> listarTotalDeConsultaPorMedico() {
        return persistence.listarTotalDeConsultaPorMedico();
    }

    public List<ConsultaDto> listarConsultasDoDia(String data) {
        return persistence.listagemConsultaPorDia(data);
    }

    public List<ConsultaDto> listarDadosCompletos() {
        return persistence.listagemCompleta();
    }

    public Consulta buscaConsultaPorId(Long id) {
        return persistence.buscaConsultaPorId(id);
    }

    public Consulta atualizaConsulta(Consulta consulta) throws IOException {
        return persistence.atualizaConsulta(consulta);
    }
}
