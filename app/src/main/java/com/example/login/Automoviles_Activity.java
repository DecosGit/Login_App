package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Automoviles_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automoviles);
    }

    public void NuevoAuto(View view) {
        Intent intent = new Intent(this, NewEmp_Activity.class);
        startActivity(intent);
    }
}