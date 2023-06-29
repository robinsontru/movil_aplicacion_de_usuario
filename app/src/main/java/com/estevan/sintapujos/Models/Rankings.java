package com.estevan.sintapujos.Models;

public class Rankings {

    String alias;
    int puntaje;

    public Rankings() {
    }

    public Rankings(String alias, int puntaje) {
        this.alias = alias;
        this.puntaje = puntaje;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Alias : " + alias +
                " " + " Puntaje = " + puntaje;
    }
}
