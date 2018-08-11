package com.asuprojects.testecollapsingtoolbar.util;

import com.asuprojects.testecollapsingtoolbar.Pessoa;

import java.util.ArrayList;
import java.util.Random;

public class GeradorPessoas {

    private static Random random = new Random();
    private static String nomes[] = new String[]{
            "Adilson", "Wilson", "Joao", "Maria", "Luciana", "Marcelo", "Mario"
    };
    private static String sobrenomes[] = new String[]{
            " da Silva", " Pereira", " Moreira de Barros", " Pinto Rosado", " Davilla", " Oliveira"
    };

    public static ArrayList<Pessoa> gerarLista(int quantidade){
        ArrayList<Pessoa> lista = new ArrayList<>();
        for(int i = 0; i < quantidade; i++){
            lista.add(new Pessoa(
                    nomes[random.nextInt(nomes.length)],
                    sobrenomes[random.nextInt(sobrenomes.length)],
                    "10-10-1978"
            ));
        }

        return lista;
    }
}