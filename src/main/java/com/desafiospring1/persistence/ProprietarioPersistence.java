//package com.desafiospring1.persistence;
//
//import com.desafiospring1.entity.Proprietario;
//
//import java.util.ArrayList;
//import java.util.List;
//
////classe especializada em realizar persistencia
//
//public class ProprietarioPersistence {
//
//    private static List<Proprietario> lista = new ArrayList<>();
//
//
//    public Proprietario cadastro(Proprietario obj) {
//        obj.setId(obj.size() + 1L);
//        obj.add(obj);
//        return obj;
//    }
//
//    public List<Proprietario> listagem() {
//        return lista;
//    }
//
//    public Proprietario obtemAnuncio(Long id) {
//        for (Proprietario obj : lista) {
//            if (obj.getId().equals(id)) {
//                return obj;
//            }
//        }
//        return null;
//    }
//
//    public void remove(Long id) {
//        for (Proprietario obj : lista) {
//            if (obj.getId().equals(id)) {
//                lista.remove(obj);
//            }
//        }
//    }
//
//    public T atualizar(Proprietario obj) {
//        for (int i = 0; i < obj.size(); i++) {
//            if (obj.get(i).getId().equals(obj.getId())) {
//                obj.set(i, obj);
//            }
//        }
//        return obj;
//    }
//}
