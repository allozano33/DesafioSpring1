package com.desafiospring1.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ConsultaJson implements UtilFile<Consulta>{

    @Override
    public String cadastrar(List<Consulta> consulta) {
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        jsonObject.put("Consulta", consulta);

        try {
            writeFile = new FileWriter("consulta.json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toJSONString();
    }

    @Override
    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("consulta.json"));
            JSONArray lista_consultas = (JSONArray) jsonObject.get("Consulta");

            for (Object con : lista_consultas) {
                Consulta con_obj = new Consulta();

                JSONObject consulta = (JSONObject) con;

                con_obj.setId((Long) consulta.get("id"));
                con_obj.setCodigo((Long) consulta.get("codigo"));

                LocalDateTime dataHora = LocalDateTime.parse((String) consulta.get("dataHora"));
                con_obj.setDataHora(dataHora);

                con_obj.setDiagnostico((String) consulta.get("diagnostico"));
                con_obj.setMotivo((String) consulta.get("motivo"));
                con_obj.setTratamento((String) consulta.get("tratamento"));

                JSONObject med = (JSONObject) consulta.get("medico");

                Medico m = new Medico();
                m.setId((Long) med.get("id"));
                m.setCrmv((String) med.get("crmv"));
                m.setCpf((String) med.get("cpf"));
                m.setNome((String) med.get("nome"));
                m.setSobrenome((String) med.get("sobrenome"));
                m.setNumeroDeRegistro((Long) med.get("numeroDeRegistro"));
                m.setEspecialidade((String) med.get("especialidade"));

                con_obj.setMedico((Medico) m);

                JSONObject ani = (JSONObject) consulta.get("animal");

                Animal a = new Animal();

                a.setId((Long) ani.get("id"));

                LocalDate dataDeNascimentoAni = LocalDate.parse((String) ani.get("dataDeNascimento"));
                a.setDataDeNascimento((dataDeNascimentoAni));

                a.setNome((String) ani.get("nome"));
                a.setNumeroPaciente((String) ani.get("numeroPaciente"));
                a.setEspecie((String) ani.get("especie"));
                a.setRaca((String) ani.get("raca"));
                a.setCor((String) ani.get("cor"));

                con_obj.setAnimal((Animal) a);

                JSONObject pro = (JSONObject) ani.get("proprietario");

                Proprietario p = new Proprietario();
                p.setId((String) pro.get("id"));
                p.setCpf((String) pro.get("cpf"));
                p.setNome((String) pro.get("nome"));
                p.setEndereco((String) pro.get("endereco"));
                p.setTelefoneContato((String) pro.get("telefoneContato"));
                p.setSobrenome((String) pro.get("sobrenome"));

                LocalDate dataDeNascimentoPro = LocalDate.parse((String) pro.get("dataDeNascimento"));
                p.setDataDeNascimento((dataDeNascimentoPro));

                con_obj.getAnimal().setProprietario((Proprietario) p);

                consultas.add(con_obj);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return consultas;
    }
}
