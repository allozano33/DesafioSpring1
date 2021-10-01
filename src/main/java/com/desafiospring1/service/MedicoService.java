package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.MedicoPersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicoService {

    private MedicoPersistence persistence;

    public MedicoService() {

    }

    public MedicoService(MedicoPersistence persistence) {
        this.persistence = persistence;
    }

    private boolean codigoNaoUtilizado(String numeroRegistro) {
        for (Medico medico : persistence.listagem()) {
            if (medico.getNumeroDeRegistro().equals(numeroRegistro)) {
                return false;
            }
        }
        return true;
    }

    public Medico cadastrar(Medico medico) throws IOException {
        if (codigoNaoUtilizado(medico.getNumeroDeRegistro())) {
            medico.setId(persistence.listagem().size()+1L);
            return persistence.cadastra(medico);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<Medico> listar() {
        return persistence.listagem();
    }

    public Medico buscaMedicoPorId(Long id) {
        return persistence.buscaMedicoPorId(id);
    }

    public boolean medicoEmConsulta(Long id) {
        ConsultaPersistence consultaPersistence = new ConsultaPersistence();
        for (ConsultaDto consultaDto : consultaPersistence.listagemCompleta()) {
            if (consultaDto.getMedico().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<Medico> deletaMedico(Long id) throws IOException {
        if(!medicoEmConsulta(id)) {
            return persistence.deletaMedico(id);
        } else {
            throw new RuntimeException("Médico em consulta, ele não pode ser deletado!");
        }
    }

    public Medico atualizaMedico(Medico medico) throws IOException {
        return persistence.atualizaMedico(medico);
    }
}
