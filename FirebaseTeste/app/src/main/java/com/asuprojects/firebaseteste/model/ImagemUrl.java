package com.asuprojects.firebaseteste.model;

public class ImagemUrl {

    private String id;
    private String nome;
    private String url;

    public ImagemUrl(String id, String nome, String url) {
        this.id = id;
        this.nome = nome;
        this.url = url;
    }

    public ImagemUrl(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public ImagemUrl() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nome: ").append(getNome()).append("\n");
        return builder.toString();
    }
}
