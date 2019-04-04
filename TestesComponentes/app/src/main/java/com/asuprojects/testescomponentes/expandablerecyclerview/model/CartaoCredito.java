package com.asuprojects.testescomponentes.expandablerecyclerview.model;

import java.util.List;

public class CartaoCredito {

    private String nome;
    private String bandeira;
    private double limite;
    private List<Compra> parcelamentos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public List<Compra> getParcelamentos() {
        return parcelamentos;
    }

    public void setParcelamentos(List<Compra> parcelamentos) {
        this.parcelamentos = parcelamentos;
    }
}
