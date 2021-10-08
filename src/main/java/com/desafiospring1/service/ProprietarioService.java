package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.ProprietarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioPersistence persistence;

    @Autowired
    private ConsultaPersistence persistenceConsulta;

    public ProprietarioService() {}
    /**
     *
     * @param persistence injeção de dependência do tipo ProprietarioPersistence
     * @author - Grupo 5 - Rafael
     */
    public ProprietarioService(ProprietarioPersistence persistence) {
        this.persistence = persistence;
    }

    /**
     * @param persistence é esperado a injeção de dependêcia do tipo ProprietarioPersistence
     * @param persistenceConsulta é esperado a injeção de dependência do tipo ConsultaPersistence
     * @author Grupo 5 - Rafael
     */
    public ProprietarioService(ProprietarioPersistence persistence, ConsultaPersistence persistenceConsulta) {
        this.persistence = persistence;
        this.persistenceConsulta = persistenceConsulta;
    }

    /**
     *
     * @param cpf é esperado um objeto do tipo cpf
     * @return boolean
     * @author - Grupo 5 - Rafael
     */
    private boolean codigoNaoUtilizado(String cpf) {
        for (Proprietario proprietario : persistence.listagem()) {
            if (proprietario.getCpf().equals(cpf)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param proprietario é esperado um objeto do tipo proprietario
     * @return o proprietario cadastrado
     * @throws IOException lança um erro caso ocorra um erro no cadastro do proprietario
     * @author - Grupo 5 - Rafael
     */
    public Proprietario cadastrar(Proprietario proprietario) throws IOException {
        if (codigoNaoUtilizado(proprietario.getCpf())) {
            proprietario.setId(persistence.listagem().size() + 1L);
            return persistence.cadastra(proprietario);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    /**
     *
     * @return list de proprietarios do tipo proprietario
     * @author - Grupo 5 - Rafael
     */
    public List<Proprietario> listar() {
        return persistence.listagem();
    }

    /**
     *
     * @param id é esperado um objeto do tipo id(Long) de um proprietario
     * @return um proprietario para o id passado como parametro
     * @author - Grupo 5 - Rafael
     */
    public Proprietario buscaProprietarioPorId(Long id) {
        return persistence.buscaProprietarioPorId(id);
    }


    /**
     *
     * @param id é esperado um objeto do tipo id(long) de um proprietario
     * @return boolean informando se proprietario esta em consulta
     * @author - Grupo 5 - Rafael
     */
    private boolean proprietarioEmConsulta(Long id) {
        for (ConsultaDto consultaDto : persistenceConsulta.listagemCompleta()) {
            if (consultaDto.getAnimalDto().getProprietario().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param id é esperado um objeto do tipo id(Long) de um proprietario
     * @return lista de proprietarios apos a remoção
     * @throws IOException lança um erro caso ocorra um erro na remoção do proprietario
     * @author - Grupo 5 - Rafael
     */
    public List<Proprietario> deletaProprietario(Long id) throws IOException {
        if (!proprietarioEmConsulta(id)) {
            return persistence.deletaProprietario(id);
        } else {
            throw new RuntimeException("Proprietario em consulta, ele não pode ser deletado!");
        }
    }

    /**
     *
     * @param proprietario é esperado um objeto do tipo id(Long) de um proprietario
     * @return Proprietario após alteração
     * @throws IOException lança um erro caso ocorra um erro na alteração do proprietario
     * @author - Grupo 5 - Rafael
     */
    public Proprietario atualizaProprietario(Proprietario proprietario) throws IOException {
        return persistence.atualizaProprietario(proprietario);
    }
}
