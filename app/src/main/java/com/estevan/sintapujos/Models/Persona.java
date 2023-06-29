package com.estevan.sintapujos.Models;

public class Persona {

    String id_persona;
    String nombre;
    String apellido;
    String tipo_documento;
    String n_documento;
    String n_ficha;
    String telefono;
    String email;
    String contrasena;
    String rol;
    String codigoRecuperar;

    String createdAt;
    String updateAt;
    String citumCitaId;
    String eventoIdEvento;

    public Persona() {
    }

    public Persona(String id_persona, String nombre, String apellido, String tipo_documento, String n_documento, String n_ficha, String telefono, String email, String contrasena, String rol, String codigoRecuperar, String createdAt, String updateAt, String citumCitaId, String eventoIdEvento) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_documento = tipo_documento;
        this.n_documento = n_documento;
        this.n_ficha = n_ficha;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
        this.codigoRecuperar = codigoRecuperar;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.citumCitaId = citumCitaId;
        this.eventoIdEvento = eventoIdEvento;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getN_documento() {
        return n_documento;
    }

    public void setN_documento(String n_documento) {
        this.n_documento = n_documento;
    }

    public String getN_ficha() {
        return n_ficha;
    }

    public void setN_ficha(String n_ficha) {
        this.n_ficha = n_ficha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigoRecuperar() {
        return codigoRecuperar;
    }

    public void setCodigoRecuperar(String codigoRecuperar) {
        this.codigoRecuperar = codigoRecuperar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCitumCitaId() {
        return citumCitaId;
    }

    public void setCitumCitaId(String citumCitaId) {
        this.citumCitaId = citumCitaId;
    }

    public String getEventoIdEvento() {
        return eventoIdEvento;
    }

    public void setEventoIdEvento(String eventoIdEvento) {
        this.eventoIdEvento = eventoIdEvento;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id_persona='" + id_persona + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipo_documento='" + tipo_documento + '\'' +
                ", n_documento='" + n_documento + '\'' +
                ", n_ficha='" + n_ficha + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                ", codigoRecuperar='" + codigoRecuperar + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", citumCitaId='" + citumCitaId + '\'' +
                ", eventoIdEvento='" + eventoIdEvento + '\'' +
                '}';
    }
}
