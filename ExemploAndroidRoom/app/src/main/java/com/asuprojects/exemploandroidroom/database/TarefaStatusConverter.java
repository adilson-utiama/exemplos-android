package com.asuprojects.exemploandroidroom.database;

import android.arch.persistence.room.TypeConverter;

import com.asuprojects.exemploandroidroom.models.enums.TarefaStatus;

public class TarefaStatusConverter {

    @TypeConverter
    public static TarefaStatus toEnum(String value){
        return TarefaStatus.toEnum(value);
    }

    @TypeConverter
    public static String toString(TarefaStatus value){
        return (value == null) ? null : value.getDescricao();
    }
}
