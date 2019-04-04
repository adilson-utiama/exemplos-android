package com.asuprojects.testesqlite3.converters;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalConverter {

    public static String toStringFormatado(BigDecimal valor){
        DecimalFormat format = new DecimalFormat("¤ ###,###.00");

        return format.format(valor.doubleValue());
    }

    public static BigDecimal toBigDecimal(String valorString){
        double valor = 0;
        try{
            valor = Double.parseDouble(valorString);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new BigDecimal(valor);
    }
}
