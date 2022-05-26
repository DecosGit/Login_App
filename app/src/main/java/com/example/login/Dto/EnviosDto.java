package com.example.login.Dto;

import com.google.type.DateTime;

import java.io.Serializable;

public class EnviosDto implements Serializable {

    String id;
    String Nombre_Cliente;
    String Vehiculo;
    String Repartidor;
    String Estado_entrega;
    String Hora_entrega;

    public EnviosDto() {
    }

    public EnviosDto(String id, String nombre_Cliente, String vehiculo, String repartidor, String estado_entrega, String hora_entrega) {
        this.id = id;
        Nombre_Cliente = nombre_Cliente;
        Vehiculo = vehiculo;
        Repartidor = repartidor;
        Estado_entrega = estado_entrega;
        Hora_entrega = hora_entrega;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        Nombre_Cliente = nombre_Cliente;
    }

    public String getVehiculo() {
        return Vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        Vehiculo = vehiculo;
    }

    public String getRepartidor() {
        return Repartidor;
    }

    public void setRepartidor(String repartidor) {
        Repartidor = repartidor;
    }

    public String getEstado_entrega() {
        return Estado_entrega;
    }

    public void setEstado_entrega(String estado_entrega) { Estado_entrega = estado_entrega; }

    public String getHora_entrega() {
        return Hora_entrega;
    }

    public void setHora_entrega(String hora_entrega) {
        Hora_entrega = hora_entrega;
    }



}
