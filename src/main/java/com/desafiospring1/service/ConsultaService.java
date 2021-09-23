package com.desafiospring1.service;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.persistence.ConsultaPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private ConsultaPersistence persistence = new ConsultaPersistence();

    private boolean codigoNaoUtilizado(String numeroPaciente) {
        for (Consulta consulta : persistence.listagem()) {
            if (consulta.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    public Consulta cadastrar(Consulta consulta) {
        if (codigoNaoUtilizado(consulta.getNumeroPaciente())) {
            consulta.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(consulta);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<Consulta> listar() {
        return persistence.listagem();
    }

    public Consulta buscaConsultaPorId(Long id) {
        return persistence.buscaConsultaPorId(id);
    }

    public List<Consulta> deletaConsulta(Long id) {
        return persistence.deletaConsulta(id);
    }

    public Consulta atualizaConsulta(Consulta consulta) {
        return persistence.atualizaConsulta(consulta);
    }
}
