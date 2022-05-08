package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Clientes_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    }

    public void NuevoCliente(View view) {
        Intent intent = new Intent(this, NewCliente_Activity.class);
        startActivity(intent);
    }
}