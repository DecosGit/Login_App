package com.example.login.Dto;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.io.Serializable;


public class VehiculosDto implements Serializable{
    public VehiculosDto() {
    }

    private String id;
    private String Placa;
    private String Marca;
    private String Color;
    private String Combustible;

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Vehiculos")
            .limitToLast(50);


    public VehiculosDto(String id, String placa, String marca, String color, String combustible) {
        setId(id);
        setPlaca(placa);
        setMarca(marca);
        setColor(color);
        setCombustible(combustible);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCombustible() {
        return Combustible;
    }

    public void setCombustible(String combustible) {
        Combustible = combustible;
    }

    @Override
    public String toString() {
        return Marca;
    }
}
