package com.asuprojects.testesqlite3.converters;

import android.util.Log;

import java.util.Calendar;

public class CalendarConverter {

    public static String toStringFormatada(Calendar data){
        String separador = "/";
        StringBuilder builder = new StringBuilder();
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH) + 1;
        int ano = data.get(Calendar.YEAR);
        builder.append(dia)
                .append(separador)
                .append(mes)
                .append(separador)
                .append(ano);
        return builder.toString();
    }

    public static Calendar toCalendar(String dataString){
        //String formato aceitavel "10/5/2018" ou "10/10/2018"
        Calendar calendar = Calendar.getInstance();


        if(dataString.matches("[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}")){
            String[] array = dataString.split("/");
            int dia = Integer.valueOf(array[0]);
            int mes = Integer.valueOf(array[1]) - 1;
            int ano = Integer.valueOf(array[2]);
            calendar.set(ano, mes, dia);

        } else {
            //throw new RuntimeException("Formato de entrada invalido");
            Log.i("CALENDAR_CONVERTER: ", dataString);
        }
        return calendar;
    }
}
