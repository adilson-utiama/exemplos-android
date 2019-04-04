package com.asuprojects.testescomponentes.expandablerecyclerview.model;

import java.util.Calendar;

public class Compra {

    private Calendar data;
    private double valor;

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
