package com.asuprojects.testesqlite3.model;

import com.asuprojects.testesqlite3.converters.BigDecimalConverter;
import com.asuprojects.testesqlite3.converters.CalendarConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class Despesa implements Serializable {

    private long _id;
    private String descricao;
    private Categoria categoriaDespesa;
    private BigDecimal valor;
    private Calendar data;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(Categoria categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getValorFormatado(){
        return BigDecimalConverter.toStringFormatado(getValor());
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public String getDataFormatada(){
        return CalendarConverter.toStringFormatada(getData());
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(get_id()).append(" DESCRICAO: ").append(getDescricao())
                .append(" DATA: ").append(getDataFormatada())
                .append(" CATEGORIA: ").append(getCategoriaDespesa().getDescricao())
                .append(" VALOR: ").append(getValorFormatado());
        return builder.toString();
    }
}
