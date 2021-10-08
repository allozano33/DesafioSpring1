package com.desafiospring1.persistence;

import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.util.ConsultaJson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsultaPersistence {

    private ConsultaJson consultaJson = new ConsultaJson();
    private static List<Consulta> listaConsultas = new ArrayList<>();

    /**
     *
     * @param consulta - é esperado do tipo Consulta
     * @return Consulta cadastrada no arquivo consulta.json
     * @throws IOException - Lança exceção caso ocorra erro no cadastro de consulta
     * @author Grupo 5 - Testers Ana Carolina e Wagner
     */
    public Consulta cadastra(Consulta consulta) throws IOException {
        consulta.setId(consultaJson.listar().size() + 1L);
        List<Consulta> novaListaConsultas = consultaJson.listar();
        novaListaConsultas.add(consulta);
        consultaJson.manipularJson(novaListaConsultas);
        return consulta;
    }

    /**
     *
     * @return List de consultas gravadas no arquivo consulta.json
     * @author Grupo 5 - Testers Ana Carolina e Wagner
     */
    public List<Consulta> listagem() {
        return consultaJson.listar();
    }

    /**
     *
     * @return List de consultas do tipo ConsultaDto com todos os dados de Proprietário, Médico e Animal cadastrados no arquivo consulta.json
     * @author Grupo 5 - Testers Ana Carolina e Wagner
     */
    public List<ConsultaDto> listagemCompleta() {
        return consultaJson.listarDadosCompletos();
    }

    /**
     *
     * @return List de consultas cadastradas no arquivo consulta.json
     * @author Grupo 5 - Testers Ana Carolina e Wagner
     */
    public List<Consulta> buscaConsultaPorId() {
        return consultaJson.listar();
    }

    /**
     *
     * @param consulta - é esperado um objeto do tipo Consulta
     * @return Uma consulta atualizada
     * @author Grupo 5 - Testers Ana Carolina e Wagner
     * @throws IOException - Lança exceção caso ocorra erro ao atualizar consulta no arquivo consulta.json
     */
    public Consulta atualizaConsulta(Consulta consulta) throws IOException {
        listaConsultas = consultaJson.listar();
        for (int i = 0; i < listaConsultas.size(); i++) {
            if (listaConsultas.get(i).getId().equals(consulta.getId())) {
                listaConsultas.set(i, consulta);
            }
        }
        consultaJson.manipularJson(listaConsultas);
        return consulta;
    }
}
