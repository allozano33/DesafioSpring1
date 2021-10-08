package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.MedicoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoPersistence persistence;

    @Autowired
    private ConsultaPersistence persistenceConsulta;

    public MedicoService() {
    }

    /**
     * @param persistence - é esperado um parâmetro do tipo MedicoPersistence para injeção de dependêcia
     * @author Grupo 5 - Tester Wesley
     */
    public MedicoService(MedicoPersistence persistence) {
        this.persistence = persistence;
    }

    /**
     * @param persistence         - é esperado um parâmetro do tipo MedicoPersistence para injeção de dependêcia
     * @param persistenceConsulta - é esperado um parâmetro do tipo ConsultaPersistence para injeção de dependêcia
     * @author Grupo 5 - Tester Wesley
     */
    public MedicoService(MedicoPersistence persistence, ConsultaPersistence persistenceConsulta) {
        this.persistence = persistence;
        this.persistenceConsulta = persistenceConsulta;
    }

    /**
     * @param numeroRegistro - é esperado o parametro numeroRegistro do medico
     * @return - retorna a verificaçao de duplicidade do código
     * @author Grupo 5 - Tester Wesley
     */
    private boolean codigoNaoUtilizado(String numeroRegistro) {
        for (Medico medico : persistence.listagem()) {
            if (medico.getNumeroDeRegistro().equals(numeroRegistro)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param medico - é esperado um objeto do tipo médico
     * @return - retorna médico cadastrado na lista
     * @throws IOException - lança exceçao "Código já utilizado" caso seja um cadastro em duplicidade
     * @author Grupo 5 - Tester Wesley
     */
    public Medico cadastrar(Medico medico) throws IOException {
        if (codigoNaoUtilizado(medico.getNumeroDeRegistro())) {
            medico.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(medico);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    /**
     * @return - retorna a lista de médicos
     * @author Grupo 5 - Tester Wesley
     */
    public List<Medico> listar() {
        return persistence.listagem();
    }

    /**
     * @param id - é esperado o parametro id do medico
     * @return - retorna item da lista médico dwe acordo com id informado
     * @author Grupo 5 - Tester Wesley
     */
    public Medico buscaMedicoPorId(Long id) {
        return persistence.buscaMedicoPorId(id);
    }

    /**
     * @param id - é esperado o parametro id do medico
     * @return - booleano que aponta se o médico está ou nao em consulta
     * @author Grupo 5 - Tester Wesley
     */
    public boolean medicoEmConsulta(Long id) {
        for (ConsultaDto consultaDto : persistenceConsulta.listagemCompleta()) {
            if (consultaDto.getMedico().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param id - é esperado o parametro id do medico
     * @return - retorna a lista médico após a deleçao
     * @throws IOException - lança exceçao caso ocorra erro na deleçao dos médicos
     * @author Grupo 5 - Tester Wesley
     */
    public List<Medico> deletaMedico(Long id) throws IOException {
        if (!medicoEmConsulta(id)) {
            return persistence.deletaMedico(id);
        } else {
            throw new RuntimeException("Médico em consulta, ele não pode ser deletado!");
        }
    }

    /**
     * @param medico - é esperado um objeto do tipo médico
     * @return - retorna a lista médico após a atualizaçao
     * @throws IOException - lança exceçao caso ocorra erro na atualizaçao dos médicos
     * @author Grupo 5 - Tester Wesley
     */
    public Medico atualizaMedico(Medico medico) throws IOException {
        return persistence.atualizaMedico(medico);
    }
}
