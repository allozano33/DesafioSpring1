package com.desafiospring1.persistence;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.util.MedicoJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicoPersistence {

    private MedicoJson medicoJson = new MedicoJson();
    private static List<Medico> listaMedicos = new ArrayList<>();

    public Medico cadastra(Medico medico) throws IOException {
        medico.setId(medicoJson.listar().size() + 1L);
        List<Medico> novaListaMedicos = medicoJson.listar();
        novaListaMedicos.add(medico);
        medicoJson.manipularJson(novaListaMedicos);
        return medico;
    }

    public List<Medico> listagem() {
        return medicoJson.listar();
    }

    public Medico buscaMedicoPorId(Long id) {
        for (Medico medico : medicoJson.listar()) {
            if (medico.getId().equals(id)) {
                return medico;
            }
        }
        return null;
    }

    public List<Medico> deletaMedico(Long id) throws IOException {
        listaMedicos = medicoJson.listar();
        for(int i=0; i<listaMedicos.size();i++){
            if(listaMedicos.get(i).getId().equals(id)){
                listaMedicos.remove(i);
            }
        }
        medicoJson.manipularJson(listaMedicos);
        return medicoJson.listar();
    }

    public Medico atualizaMedico(Medico medico) throws IOException {
        listaMedicos = medicoJson.listar();
        for (int i = 0; i < listaMedicos.size(); i++) {
            if (listaMedicos.get(i).getId().equals(medico.getId())) {
                listaMedicos.set(i, medico);
            }
        }
        medicoJson.manipularJson(listaMedicos);
        return medico;
    }
}
