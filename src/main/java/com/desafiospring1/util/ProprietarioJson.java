package com.desafiospring1.util;

import com.desafiospring1.entity.Proprietario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProprietarioJson implements UtilFile<Proprietario>{

    @Override
    public String cadastrar(List<Proprietario> proprietarios) {

        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        jsonObject.put("Proprietario", proprietarios);

        try {
            writeFile = new FileWriter("proprietario.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toJSONString();
    }

    @Override
    public String manipularJson (java.util.List<Proprietario> obj) {
        return null;
    }

    @Override
    public List<Proprietario> listar() {
        List<Proprietario> proprietarios = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("proprietario.json"));
            JSONArray lista_proprietarios = (JSONArray) jsonObject.get("Proprietario");

            for (Object pro : lista_proprietarios) {
                Proprietario pro_obj = new Proprietario();

                JSONObject proprietario = (JSONObject) pro;

                pro_obj.setId((String) proprietario.get("id"));
                pro_obj.setCpf((String) proprietario.get("cpf"));
                pro_obj.setNome((String) proprietario.get("nome"));
                pro_obj.setSobrenome((String) proprietario.get("sobrenome"));

                LocalDate dataDeNascimento = LocalDate.parse((String) proprietario.get("dataDeNascimento"));
                pro_obj.setDataDeNascimento((dataDeNascimento));

                pro_obj.setEndereco((String) proprietario.get("endereco"));
                pro_obj.setTelefoneContato((String) proprietario.get("telefoneContato"));

                proprietarios.add(pro_obj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }
}
