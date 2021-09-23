package com.desafiospring1.service;


import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.persistence.AnimalPersistence;
import com.desafiospring1.persistence.ProprietarioPersistence;

import java.util.List;

public class ProprietarioService {
    private ProprietarioPersistence persistence = new ProprietarioPersistence();

    private boolean codigoNaoUtilizado(String numeroPaciente) {
        for (Proprietario proprietario : persistence.listagem()) {
            if (proprietario.getNumeroPaciente().equals(numeroPaciente)) {
                return false;
            }
        }
        return true;
    }

    public Proprietario cadastrar(Proprietario proprietario) {
        if (codigoNaoUtilizado(proprietario.getNumeroPaciente())) {
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

    public Animal atualizaProprietario(Proprietario proprietario){
        return persistence.atualizaProprietario(buscaProprietarioPorId());
    }
}
