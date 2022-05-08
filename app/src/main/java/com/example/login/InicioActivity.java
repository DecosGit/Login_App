package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class InicioActivity extends AppCompatActivity {

    private Button btSalir;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btSalir = findViewById(R.id.btSal);

        mAuth = FirebaseAuth.getInstance();

        btSalir.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
        });
    }



    public void opPiloto(View view) {
        Intent inicio = new Intent(this, Empleados.class);
        startActivity(inicio);

    }

    public void Vehiculo(View view) {
        Intent inicio = new Intent(this, Automoviles_Activity.class);
        startActivity(inicio);

    }

    public void Cliente(View view) {
        Intent inicio = new Intent(this, Clientes_Activity.class);
        startActivity(inicio);

    }
}