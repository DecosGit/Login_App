package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }


    public void opPiloto(View view) {
        Intent inicio = new Intent(this, Empleados.class);
        startActivity(inicio);

    }

    public void Vehiculo(View view) {
        Intent inicio = new Intent(this, Automoviles_Activity.class);
        startActivity(inicio);

    }
}