package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Empleados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);


    }

    public void NuevoEmpleado(View view) {
        Intent intent = new Intent(this, NewEmp_Activity.class);
        startActivity(intent);
    }
}