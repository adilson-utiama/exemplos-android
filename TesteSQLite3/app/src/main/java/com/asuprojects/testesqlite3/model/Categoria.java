package com.asuprojects.testesqlite3.model;

public enum Categoria {

    SUPERMERCADO("Supermercado"),
    LANCHES("Lanches"),
    CONTAS("Contas"),
    REMEDIOS("Remedios"),
    SEGURANCA("Segurança"),
    TRANSPORTE("Transporte"),
    CURSOS("Cursos"),
    UTILITARIOS("Utilitarios"),
    ELETRONICOS("Produtos Eletronicos"),
    SEM_CATEGORIA("Sem Categoria");

    private String descricao;

    Categoria(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
