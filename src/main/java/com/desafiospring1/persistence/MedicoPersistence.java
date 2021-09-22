//package com.desafiospring1.persistence;
//
//import com.desafiospring1.entity.Medico;
//
//import java.util.ArrayList;
//import java.util.List;
//
////classe especializada em realizar persistencia
//
//public class MedicoPersistence {
//
//    private static List<Medico> lista = new ArrayList<>();
//
//
//    public Medico cadastro(Medico obj) {
//        obj.setId(obj.size() + 1L);
//        obj.add(obj);
//        return obj;
//    }
//
//    public List<Medico> listagem() {
//        return lista;
//    }
//
//    public Medico obtemAnuncio(Long id) {
//        for (Medico obj : lista) {
//            if (obj.getId().equals(id)) {
//                return obj;
//            }
//        }
//        return null;
//    }
//
//    public void remove(Long id) {
//        for (Medico obj : lista) {
//            if (obj.getId().equals(id)) {
//                lista.remove(obj);
//            }
//        }
//    }
//
//    public Medico atualizar(Medico obj) {
//        for (int i = 0; i < obj.size(); i++) {
//            if (obj.get(i).getId().equals(obj.getId())) {
//                obj.set(i, obj);
//            }
//        }
//        return obj;
//    }
//}
