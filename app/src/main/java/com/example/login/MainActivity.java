package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //metodos para hacer los saltos de pantalla necesarios

    public void Inicio(View view){
        Intent inicio = new Intent(this, InicioActivity.class);
        startActivity(inicio);
    }
}