package com.desafiospring1.service;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.persistence.MedicoPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private MedicoPersistence persistence = new MedicoPersistence();

    private boolean codigoNaoUtilizado(String crmv) {
        for (Medico medico : persistence.listagem()) {
            if (medico.getCrmv().equals(crmv)) {
                return false;
            }
        }
        return true;
    }

    public Medico cadastrar(Medico medico) {
        if (codigoNaoUtilizado(medico.getCrmv())) {
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

    public List<Medico> deletaMedico(Long id) {
        return persistence.deletaMedico(id);
    }

    public Medico atualizaMedico(Medico medico){
        return persistence.atualizaMedico(medico);
    }
}
