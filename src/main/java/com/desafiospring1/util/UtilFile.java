package com.desafiospring1.util;

import java.util.List;

public interface UtilFile<T>{

    String cadastrar(List<T> obj);

    List<T> listar();

    String atualizar(List<T> obj);

}
