package com.example.login.Dto;

import java.io.Serializable;

public class EmpleadosDto implements Serializable {
    public EmpleadosDto() {
    }

    private String Id;
    private String NombreCompleto;
    private String Telefono;
    private String Correo;
    private String Contraseña;


    public EmpleadosDto(String id, String nombreCompleto, String telefono, String correo, String contraseña) {
        setId(id);
        setNombreCompleto(nombreCompleto);
        setTelefono(telefono);
        setCorreo(correo);
        setContraseña(contraseña);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getTelefono() { return Telefono; }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {return NombreCompleto;}
}
