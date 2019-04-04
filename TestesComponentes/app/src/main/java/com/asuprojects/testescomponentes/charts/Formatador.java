package com.asuprojects.testescomponentes.charts;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Formatador implements IValueFormatter {

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        NumberFormat format = DecimalFormat.getCurrencyInstance();
        String valor = format.format((double) value);
        return valor;
    }
}