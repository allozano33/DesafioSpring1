package com.desafiospring1.util;

import com.desafiospring1.entity.Medico;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MedicoJson implements UtilFile<Medico>{

    @Override
    public String manipularJson(List<Medico> medicos) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Medico", medicos);
        FileWriter writeFile = new FileWriter("medico.json");

        try {
            writeFile.write(jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writeFile.close();
        }
        return jsonObject.toJSONString();
    }

    @Override
    public List<Medico> listar() {
        List<Medico> medicos = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        File file = new File("medico.json");

        try {
            if (file.length() != 0) {
                jsonObject = (JSONObject) parser.parse(new FileReader("medico.json"));
                JSONArray lista_medicos = (JSONArray) jsonObject.get("Medico");

                for (Object med : lista_medicos) {
                    Medico m = new Medico();

                    JSONObject medico = (JSONObject) med;

                    m.setId((Long) medico.get("id"));
                    m.setCpf((String) medico.get("cpf"));
                    m.setNome((String) medico.get("nome"));
                    m.setSobrenome((String) medico.get("sobrenome"));
                    m.setEspecialidade((String) medico.get("especialidade"));
                    m.setNumeroDeRegistro((String) medico.get("numeroDeRegistro"));

                    medicos.add(m);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return medicos;
    }
}
