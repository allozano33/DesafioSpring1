package com.desafiospring1.persistence;

import com.desafiospring1.entity.Medico;
import com.desafiospring1.util.MedicoJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicoPersistence {

    private MedicoJson medicoJson = new MedicoJson();
    private static List<Medico> listaMedicos = new ArrayList<>();

    /**
     * @param medico - é esperado um objeto do tipo médico
     * @return - retorna médico cadastrado no arquivo medicoJson
     * @throws IOException - lança exceçao caso ocorra erro no cadastro do médico
     * @author Grupo 5 - Tester Wesley
     */
    public Medico cadastra(Medico medico) throws IOException {
        medico.setId(medicoJson.listar().size() + 1L);
        List<Medico> novaListaMedicos = medicoJson.listar();
        novaListaMedicos.add(medico);
        medicoJson.manipularJson(novaListaMedicos);
        return medico;
    }

    /**
     * @return - retorna a lista de médicos do arquivo medicoJson
     * @author Grupo 5 - Tester Wesley
     */
    public List<Medico> listagem() {
        return medicoJson.listar();
    }

    /**
     * @param id - é esperado o parametro id do medico
     * @return - retorna item da lista médico de acordo com id informado do arquivo medicoJson
     * @author Grupo 5 - Tester Wesley
     */
    public Medico buscaMedicoPorId(Long id) {
        for (Medico medico : medicoJson.listar()) {
            if (medico.getId().equals(id)) {
                return medico;
            }
        }
        return null;
    }

    /**
     * @param id - é esperado o parametro id do medico
     * @return - retorna a lista médico após a deleçao do arquivo medicoJson
     * @throws IOException - lança exceçao caso ocorra erro na deleçao dos médicos
     * @author Grupo 5 - Tester Wesley
     */
    public List<Medico> deletaMedico(Long id) throws IOException {
        listaMedicos = medicoJson.listar();
        for (int i = 0; i < listaMedicos.size(); i++) {
            if (listaMedicos.get(i).getId().equals(id)) {
                listaMedicos.remove(i);
            }
        }
        medicoJson.manipularJson(listaMedicos);
        return medicoJson.listar();
    }

    /**
     * @param medico - é esperado um objeto do tipo médico
     * @return - retorna a lista médico após a atualizaçao do arquivo medicoJson
     * @throws IOException - lança exceçao caso ocorra erro na atualizaçao dos médicos
     * @author Grupo 5 - Tester Wesley
     */
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
