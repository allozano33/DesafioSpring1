package com.desafiospring1.persistence;

import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.util.ProprietarioJson;

import java.util.ArrayList;
import java.util.List;

public class ProprietarioPersistence {

    private ProprietarioJson proprietarioJson = new ProprietarioJson();
    private static List<Proprietario> listaProprietario = new ArrayList<>();

    public Proprietario cadastra(Proprietario proprietario) {
        proprietario.setId(proprietarioJson.listar().size() + 1L);
        for (Proprietario proprietarios: proprietarioJson.listar()) {
            listaProprietario.add(proprietarios);
        }
        listaProprietario.add(proprietario);
        proprietarioJson.manipularJson(listaProprietario);
        return proprietario;
    }

    public List<Proprietario> listagem() {
        return proprietarioJson.listar();
    }

    public Proprietario buscaProprietarioPorId(Long id) {
        for (Proprietario proprietario : proprietarioJson.listar()) {
            if (proprietario.getId().equals(id)) {
                return proprietario;
            }
        }
        return null;
    }

    public List<Proprietario> deletaProprietario(Long id) {
        listaProprietario = proprietarioJson.listar();
        for(int i=0; i<listaProprietario.size();i++){
            if(listaProprietario.get(i).getId().equals(id)){
                listaProprietario.remove(i);
            }
        }
       proprietarioJson.manipularJson(listaProprietario);
        return proprietarioJson.listar();
    }

    public Proprietario atualizaProprietario(Proprietario proprietario){
        listaProprietario = proprietarioJson.listar();
        for (int i = 0; i < listaProprietario.size(); i++) {
            if (listaProprietario.get(i).getId().equals(proprietario.getId())) {
                listaProprietario.set(i, proprietario);
            }
        }
        proprietarioJson.manipularJson(listaProprietario);
        return proprietario;
    }
}
