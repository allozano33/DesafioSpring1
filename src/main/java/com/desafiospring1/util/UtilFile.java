package com.desafiospring1.util;

import java.io.IOException;
import java.util.List;

public interface UtilFile<T>{

    String manipularJson(List<T> obj) throws IOException;

    List<T> listar();

}
