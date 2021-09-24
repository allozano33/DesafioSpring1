package com.desafiospring1.util;

import com.desafiospring1.dto.AnimalDto;
import com.desafiospring1.dto.ConsultaDto;
import com.desafiospring1.entity.Animal;
import com.desafiospring1.entity.Consulta;
import com.desafiospring1.entity.Medico;
import com.desafiospring1.entity.Proprietario;
import com.desafiospring1.service.AnimalService;
import com.desafiospring1.service.MedicoService;
import com.desafiospring1.service.ProprietarioService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaJson implements UtilFile<Consulta>, ListagemCompleta<ConsultaDto>{

    @Override
    public String manipularJson(List<Consulta> consulta) {
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
                con_obj.setCodigo((String) consulta.get("codigo"));

                LocalDateTime dataHora = LocalDateTime.parse((String) consulta.get("dataHora"));
                con_obj.setDataHora(dataHora);

                con_obj.setDiagnostico((String) consulta.get("diagnostico"));
                con_obj.setMotivo((String) consulta.get("motivo"));
                con_obj.setTratamento((String) consulta.get("tratamento"));
                con_obj.setIdMedico((Long) consulta.get("idMedico"));
                con_obj.setIdAnimal((Long) consulta.get("idAnimal"));

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

    @Override
    public List<ConsultaDto> listarDadosCompletos() {
        List<ConsultaDto> consultas = new ArrayList<>();
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("consulta.json"));
            JSONArray lista_consultas = (JSONArray) jsonObject.get("Consulta");

            for (Object con : lista_consultas) {
                ConsultaDto con_obj = new ConsultaDto();

                JSONObject consulta = (JSONObject) con;

                con_obj.setId((Long) consulta.get("id"));
                con_obj.setCodigo((String) consulta.get("codigo"));

                LocalDateTime dataHora = LocalDateTime.parse((String) consulta.get("dataHora"));
                con_obj.setDataHora(dataHora);

                con_obj.setDiagnostico((String) consulta.get("diagnostico"));
                con_obj.setMotivo((String) consulta.get("motivo"));
                con_obj.setTratamento((String) consulta.get("tratamento"));

                MedicoService medicoService = new MedicoService();
                Medico m = medicoService.buscaMedicoPorId((Long) consulta.get("idMedico"));

                Medico medico = new Medico();
                medico.setId(m.getId());
                medico.setCpf(m.getCpf());
                medico.setNome(m.getNome());
                medico.setSobrenome(m.getSobrenome());
                medico.setNumeroDeRegistro(m.getNumeroDeRegistro());
                medico.setEspecialidade(m.getEspecialidade());

                con_obj.setMedico((medico));

                AnimalService animalService = new AnimalService();
                Animal a = animalService.buscaAnimalPorId((Long) consulta.get("idAnimal"));

                AnimalDto animal = new AnimalDto();

                animal.setId(a.getId());
                animal.setNome(a.getNome());
                animal.setNumeroPaciente(a.getNumeroPaciente());
                animal.setEspecie(a.getEspecie());
                animal.setRaca(a.getRaca());
                animal.setCor(a.getCor());
                animal.setDataDeNascimento(a.getDataDeNascimento());

                con_obj.setAnimalDto(animal);

                ProprietarioService proprietarioService = new ProprietarioService();
                Proprietario p = proprietarioService.buscaProprietarioPorId(a.getIdProprietario());

                Proprietario proprietario = new Proprietario();

                proprietario.setId(p.getId());
                proprietario.setCpf(p.getCpf());
                proprietario.setNome(p.getNome());
                proprietario.setEndereco(p.getEndereco());
                proprietario.setTelefoneContato(p.getTelefoneContato());
                proprietario.setSobrenome(p.getSobrenome());
                proprietario.setDataDeNascimento(p.getDataDeNascimento());

                con_obj.getAnimalDto().setProprietario(proprietario);

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
