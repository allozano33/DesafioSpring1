package com.desafiospring1.util;

import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Proprietario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalJson implements UtilFile<Animal>{

    @Override
    public String cadastrar(List<Animal> animal) {
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        jsonObject.put("Animal", animal);

        try {
            writeFile = new FileWriter("animal.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toJSONString();
    }

    @Override
    public List<Animal> listar() {
        List<Animal> animais = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("animal.json"));
            JSONArray lista_animais = (JSONArray) jsonObject.get("Animal");

            for (Object ani : lista_animais) {
                Animal ani_obj = new Animal();

                JSONObject animal = (JSONObject) ani;

                ani_obj.setId((Long) animal.get("id"));
                ani_obj.setNome((String) animal.get("nome"));
                ani_obj.setNumeroPaciente((String) animal.get("numeroPaciente"));
                ani_obj.setEspecie((String) animal.get("especie"));
                ani_obj.setRaca((String) animal.get("raca"));
                ani_obj.setCor((String) animal.get("cor"));

                LocalDate dataDeNascimento = LocalDate.parse((String) animal.get("dataDeNascimento"));
                ani_obj.setDataDeNascimento((dataDeNascimento));

                JSONObject obj = (JSONObject) animal.get("proprietario");

                Proprietario p = new Proprietario();
                p.setCpf((String) obj.get("cpf"));
                p.setNome((String) obj.get("nome"));
                p.setEndereco((String) obj.get("endereco"));
                p.setTelefoneContato((String) obj.get("telefoneContato"));
                p.setSobrenome((String) obj.get("sobrenome"));

                LocalDate dataDeNascimentoPro = LocalDate.parse((String) obj.get("dataDeNascimento"));
                p.setDataDeNascimento((dataDeNascimentoPro));

                ani_obj.setProprietario((Proprietario) p);

                animais.add(ani_obj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return animais;
    }

    @Override
    public String atualizar(List<Animal> animal) {
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        jsonObject.put("Animal", animal);

        try {
            writeFile = new FileWriter("animal.json");
            writeFile.append(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toJSONString();
    }

}
