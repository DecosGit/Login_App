package com.example.login.Dto;

import android.app.DownloadManager;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.util.Objects;

public class ClientesDto implements Serializable {
    public ClientesDto() {
    }

    private String id;
    private String NombreCliente;
    private String TelefonoCliente;
    private String Direccion;
    private String Horario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientesDto that = (ClientesDto) o;
        return TelefonoCliente == that.TelefonoCliente && Objects.equals(id, that.id) && Objects.equals(NombreCliente, that.NombreCliente) && Objects.equals(Direccion, that.Direccion) && Objects.equals(Horario, that.Horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, NombreCliente, TelefonoCliente, Direccion, Horario);
    }

    public ClientesDto(String id, String nombre){
        setId(id);
        setNombreCliente(nombre);
    }

    public ClientesDto(String id, String nombreCliente, String telefonoCliente, String direccion, String horario) {
        setId(id);
        setNombreCliente(nombreCliente);
        setTelefonoCliente(telefonoCliente);
        setDireccion(direccion);
        setHorario(horario);
    }

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Clientes")
            .limitToLast(50);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return TelefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        TelefonoCliente = telefonoCliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

}
