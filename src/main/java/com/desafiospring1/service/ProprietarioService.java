package com.desafiospring1.service;

import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.ProprietarioPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {
    private ProprietarioPersistence persistence = new ProprietarioPersistence();

    private boolean codigoNaoUtilizado(String cpf) {
        for (Proprietario proprietario : persistence.listagem()) {
            if (proprietario.getCpf().equals(cpf)) {
                return false;
            }
        }
        return true;
    }

    public Proprietario cadastrar(Proprietario proprietario) {
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

    public List<Proprietario> deletaProprietario(Long id) {
        return persistence.deletaProprietario(id);
    }

    public Proprietario atualizaProprietario(Proprietario proprietario){
        return persistence.atualizaProprietario(proprietario);
    }
}
