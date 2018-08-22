package com.asuprojects.exemploandroidroom.models.enums;

public enum TarefaStatus {

    NEW("NEW"),
    EDIT("EDIT"),
    CREATED("CREATED"),
    MODIFIED("MODIFIED"),
    CANCELED("CANCELED"),
    DONE("DONE");

    private String descricao;

    private TarefaStatus(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TarefaStatus toEnum(String s){
        for(TarefaStatus t : TarefaStatus.values()){
            if(s.equals(t.toString())){
                return t;
            }
        }
        return null;
    }
}
