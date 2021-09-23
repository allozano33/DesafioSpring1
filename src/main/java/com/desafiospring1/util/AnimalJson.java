package com.desafiospring1.util;

import com.desafiospring1.dto.AnimalDto;
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

public class AnimalJson implements UtilFile<AnimalDto>{

    @Override
    public String manipularJson(List<AnimalDto> animalDto) {
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        jsonObject.put("Animal", animalDto);

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
    public List<AnimalDto> listar() {
        List<AnimalDto> animais = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("animal.json"));
            JSONArray lista_animais = (JSONArray) jsonObject.get("Animal");

            for (Object ani : lista_animais) {
                AnimalDto ani_obj = new AnimalDto();

                JSONObject animal = (JSONObject) ani;

                ani_obj.setId((Long) animal.get("id"));
                ani_obj.setNome((String) animal.get("nome"));
                ani_obj.setNumeroPaciente((String) animal.get("numeroPaciente"));
                ani_obj.setEspecie((String) animal.get("especie"));
                ani_obj.setRaca((String) animal.get("raca"));
                ani_obj.setCor((String) animal.get("cor"));

                LocalDate dataDeNascimento = LocalDate.parse((String) animal.get("dataDeNascimento"));
                ani_obj.setDataDeNascimento((dataDeNascimento));

                ani_obj.setId((Long) animal.get("idProprietario"));

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
}
