package com.asuprojects.firebaseteste.model;

public class Produto {

    private String nome;
    private String descricao;
    private double preco;

    public Produto() {}

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(getNome()).append("\n")
                .append("Descricao: ").append(getDescricao()).append("\n")
                .append("Preço: ").append(getPreco());
        return builder.toString();
    }
}
