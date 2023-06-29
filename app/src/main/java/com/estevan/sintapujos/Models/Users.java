package com.estevan.sintapujos.Models;

public class Users {

    String alias;
    String puntaje;


    public Users() {
    }

    public Users(String alias, String puntaje) {
        this.alias = alias;
        this.puntaje = puntaje;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }


    @Override
    public String toString() {
        return "Users{" +
                "alias='" + alias + '\'' +
                ", puntaje='" + puntaje + '\'' +
                '}';
    }
}
