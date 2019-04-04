package com.asuprojects.testesqlite3.helper;

import com.asuprojects.testesqlite3.model.Categoria;

public class CategoriaUtil {

    public static Categoria getCategoriaFrom(String valorString){
        Categoria[] values = Categoria.values();
        for (Categoria cat : values){
            if (cat.getDescricao().equals(valorString)){
                return cat;
            }
        }
        return Categoria.SEM_CATEGORIA;
    }
}
