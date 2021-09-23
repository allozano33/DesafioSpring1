package com.desafiospring1.util;

import java.util.List;

public interface UtilFile<T>{

    String manipularJson(List<T> obj);

    List<T> listar();

}
