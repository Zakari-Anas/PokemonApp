package com.example.pokemon.Model;

public class NextEv {
    private String num ;
    private String name ;

    public NextEv() {
    }

    public NextEv(String num, String name) {
        this.num = num;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
