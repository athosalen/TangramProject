package com.example.athos.cg;

public class ItemMenu {

    private String id;
    private String nome;

    public ItemMenu(String id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
