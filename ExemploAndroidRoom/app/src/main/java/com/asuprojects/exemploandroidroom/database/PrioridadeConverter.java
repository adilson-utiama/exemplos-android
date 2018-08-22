package com.asuprojects.exemploandroidroom.database;

import android.arch.persistence.room.TypeConverter;

import com.asuprojects.exemploandroidroom.models.enums.Prioridade;

public class PrioridadeConverter {

    @TypeConverter
    public static Prioridade toEnum(String value){
        return Prioridade.toEnum(value);
    }

    @TypeConverter
    public static String toString(Prioridade value){
        return (value == null) ? null : value.getDescricao();
    }
}
