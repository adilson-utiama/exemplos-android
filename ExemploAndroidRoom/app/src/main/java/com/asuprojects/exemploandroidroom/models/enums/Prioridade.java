package com.asuprojects.exemploandroidroom.models.enums;

public enum Prioridade {


    LOW("LOW"),
    MIDDLE("MIDDLE"),
    HIGH("HIGH"),
    URGENT("URGENT");

    private String descricao;

    private Prioridade(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(String s){
        for (Prioridade p : Prioridade.values()) {
            if(s.equals(p.toString())){
                return p;
            }
        }
        return null;
    }

}
