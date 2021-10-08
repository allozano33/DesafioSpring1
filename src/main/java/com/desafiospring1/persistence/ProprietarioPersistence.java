package com.desafiospring1.persistence;

import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.util.ProprietarioJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProprietarioPersistence {

    private ProprietarioJson proprietarioJson = new ProprietarioJson();
    private static List<Proprietario> listaProprietario = new ArrayList<>();

    /**
     *
     * @param proprietario esperado tipo Proprietario
     * @return Proprietario em arquivo Json
     * @throws IOException
     * @author Grupo 5 - Rafael
     */
    public Proprietario cadastra(Proprietario proprietario) throws IOException {
        proprietario.setId(proprietarioJson.listar().size() + 1L);
        List<Proprietario> novaListaProprietario = proprietarioJson.listar();
        novaListaProprietario.add(proprietario);
        proprietarioJson.manipularJson(novaListaProprietario);
        return proprietario;
    }

    /**
     *
     * @return List de proprietarios em arquivo Json
     @author Grupo 5 - Rafael
     */
    public List<Proprietario> listagem() {
        return proprietarioJson.listar();
    }

    /**
     *
     * @param id é esperado um objeto do tipo id(Long) de um proprietario
     * @return List de Proprietarios em arquivo Json através do parametro id
     * @author Grupo 5 - Rafael
     */
    public Proprietario buscaProprietarioPorId(Long id) {
        for (Proprietario proprietario : proprietarioJson.listar()) {
            if (proprietario.getId().equals(id)) {
                return proprietario;
            }
        }
        return null;
    }

    /**
     *
     * @param id é esperado um objeto do tipo id(Long) de um proprietario
     * @return List de proprietario atualizada após a remoção
     * @throws IOException
     * @author Grupo 5 - Rafael
     */
    public List<Proprietario> deletaProprietario(Long id) throws IOException {
        listaProprietario = proprietarioJson.listar();
        for(int i=0; i<listaProprietario.size();i++){
            if(listaProprietario.get(i).getId().equals(id)){
                listaProprietario.remove(i);
            }
        }
        proprietarioJson.manipularJson(listaProprietario);
        return proprietarioJson.listar();
    }

    /**
     *
     * @param proprietario é esperado um objeto do tipo proprietario
     * @return List de proprietarios atualizada após uma alteração
     * @throws IOException
     * @author Grupo 5 - Rafael
     */
    public Proprietario atualizaProprietario(Proprietario proprietario) throws IOException {
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
