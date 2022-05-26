package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.Dao.DaoEmpleados;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.ClientesDto;
import com.example.login.Dto.EmpleadosDto;
import com.example.login.Dto.VehiculosDto;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class NewEmp_Activity extends AppCompatActivity {

    TextInputLayout layNombre, layTelefono, layCorreo, layContraseña;
    EditText edtNombre, edtTelefono, edtCorreo, edtContraseña;
    Button ModificarCar, Guardar;
    DaoEmpleados carSetting;
    String keyVehiculo="";

    FirebaseDatabase firebase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emp);

        layNombre = findViewById(R.id.layNombre);
        layTelefono = findViewById(R.id.layTelefono);
        layCorreo = findViewById(R.id.layCorreo);
        layContraseña = findViewById(R.id.layContraseña);
        edtNombre = findViewById(R.id.edtNombreEmpleado);
        edtTelefono = findViewById(R.id.edtNumTel);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContraseña = findViewById(R.id.edtPass);
        Guardar = (Button) findViewById(R.id.GuardarEmpleado);


        inicializarFirebase();
      /*  EmpleadosDto car = (EmpleadosDto)getIntent().getSerializableExtra("Edit");
        if(car!=null){
            Guardar.setText("Modificar");                 arreglar el metodo

            inpPlaca.setText(car.getPlaca());
            inpMarca.setText(car.getMarca());
            inpColor.setText(car.getColor());
            inpCombustible.setText(car.getCombustible());
        }else{
            Guardar.setText("Submit");
        }*/

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);
        databaseReference = firebase.getReference();
    }

    public void SaveAuto(View view) {
        Validar();

        finish();

    }

    private void Validar(){
        inicializarFirebase();
        String nombreEmp = edtNombre.getText().toString();
        String telefonoEmp = edtTelefono.getText().toString();
        String correo = edtCorreo.getText().toString();
        String password = edtContraseña.getText().toString();



        if(nombreEmp.equals("")){
            edtNombre.setError("Requerido");
        }
        if(telefonoEmp.equals("")){
            edtTelefono.setError("Requerido");
        }
        if(correo.equals("")){
            edtCorreo.setError("Requerido");
        }
        if(password.equals("")){
            edtContraseña.setError("Requerido");
        }else{

            EmpleadosDto emp = new EmpleadosDto();
            if(emp!=null) {
                emp.setId(UUID.randomUUID().toString());
                emp.setNombreCompleto(nombreEmp);
                emp.setTelefono(telefonoEmp);
                emp.setCorreo(correo);
                emp.setContraseña(password);
                databaseReference.child("Empleados").child(emp.getId()).setValue(emp);
                Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                LimpiarCajas();
            }else{
                //este metodo es para modificar vehiculos
                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("id", emp.getId());
                hashmap.put("nombreCompleto", edtNombre.getText().toString());
                hashmap.put("telefono", edtTelefono.getText().toString());
                hashmap.put("correo", edtCorreo.getText().toString());
                hashmap.put("contraseña", edtContraseña.getText().toString());
                carSetting.update(emp.getId(), hashmap).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();
                    finish();
                }).addOnFailureListener(er ->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    private void LimpiarCajas() {
        edtNombre.setText("");
        edtTelefono.setText("");
        edtCorreo.setText("");
        edtContraseña.setText("");
    }
}