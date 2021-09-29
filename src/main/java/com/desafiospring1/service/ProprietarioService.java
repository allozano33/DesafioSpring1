package com.desafiospring1.service;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ConsultaPersistence;
import com.desafiospring1.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProprietarioService {
    private ProprietarioPersistence persistence;

    public ProprietarioService() {

    }

    public ProprietarioService(ProprietarioPersistence persistence) {
        this.persistence = persistence;
    }

    private boolean codigoNaoUtilizado(String cpf) {
        for (Proprietario proprietario : persistence.listagem()) {
            if (proprietario.getCpf().equals(cpf)) {
                return false;
            }
        }
        return true;
    }

    public Proprietario cadastrar(Proprietario proprietario) throws IOException {
        if (codigoNaoUtilizado(proprietario.getCpf())) {
            proprietario.setId(persistence.listagem().size()+1L);
            return persistence.cadastra(proprietario);
        } else {
            throw new RuntimeException("Código já utilizado");
        }
    }

    public List<Proprietario> listar() {
        return persistence.listagem();
    }

    public Proprietario buscaProprietarioPorId(Long id) {
        return persistence.buscaProprietarioPorId(id);
    }

    private boolean proprietarioEmConsulta(Long id) {
        ConsultaPersistence consultaPersistence = new ConsultaPersistence();
        for (ConsultaDto consultaDto : consultaPersistence.listagemCompleta()) {
            if (consultaDto.getAnimalDto().getProprietario().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<Proprietario> deletaProprietario(Long id) throws IOException {
        if(!proprietarioEmConsulta(id)) {
            return persistence.deletaProprietario(id);
        } else {
            throw new RuntimeException("Proprietario em consulta, ele não pode ser deletado!");
        }

    }

    public Proprietario atualizaProprietario(Proprietario proprietario) throws IOException {
        return persistence.atualizaProprietario(proprietario);
    }
}
