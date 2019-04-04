package com.asuprojects.firebaseteste.model;

public class Usuario {

    private String nome;
    private String sobrenome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(getNome()).append("\n")
                .append("Sobrenome: ").append(getSobrenome()).append("\n")
                .append("Idade: ").append(getIdade());
        return builder.toString();
    }
}
