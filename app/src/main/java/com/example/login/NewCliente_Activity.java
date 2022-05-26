package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.Dao.DaoClientes;
import com.example.login.Dto.ClientesDto;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class NewCliente_Activity extends AppCompatActivity {

    TextInputLayout layNombre, layTelefono, layDireccion, layHorario;
    EditText edtNombreCliente, edtTelefonoCliente, edtDireccion, edtHorario;
    Button ModificarCar, Guardar;
    DaoClientes carSetting;
    String keyVehiculo="";

    FirebaseDatabase firebase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cliente);

        layNombre = findViewById(R.id.layNombre);
        layTelefono = findViewById(R.id.layTelefono);
        layDireccion = findViewById(R.id.layDireccion);
        layHorario = findViewById(R.id.layHorario);
        edtNombreCliente =findViewById(R.id.edtNombreClient);
        edtTelefonoCliente = findViewById(R.id.edtNumTelClien);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtHorario = findViewById(R.id.edt_horario);
        Guardar = (Button) findViewById(R.id.GuardarCliente);

        inicializarFirebase();
        /*  ClientesDto car = (ClientesDto)getIntent().getSerializableExtra("Edit");
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
        validacion();
        finish();

    }

    private void validacion(){

        inicializarFirebase();
        String nombreCliente = edtNombreCliente.getText().toString();
        String telefonoCliente = edtTelefonoCliente.getText().toString();
        String direccion = edtDireccion.getText().toString();
        String horario = edtHorario.getText().toString();



        if(nombreCliente.equals("")){
            edtNombreCliente.setError("Requerido");
        }
        if(telefonoCliente.equals("")){
            edtTelefonoCliente.setError("Requerido");
        }
        if(direccion.equals("")){
            edtDireccion.setError("Requerido");
        }
        if(horario.equals("")){
            edtHorario.setError("Requerido");
        }else {
            ClientesDto auto = new ClientesDto();
            if (auto != null) {
                auto.setId(UUID.randomUUID().toString());
                auto.setNombreCliente(nombreCliente);
                auto.setTelefonoCliente(telefonoCliente);
                auto.setDireccion(direccion);
                auto.setHorario(horario);
                databaseReference.child("Clientes").child(auto.getId()).setValue(auto);
                Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                LimpiarCajas();
            } else {
                //este metodo es para modificar vehiculos
                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("id", auto.getId());
                hashmap.put("nombreCliente", edtNombreCliente.getText().toString());
                hashmap.put("telefonoCliente", edtTelefonoCliente.getText().toString());
                hashmap.put("direccion", edtDireccion.getText().toString());
                hashmap.put("horario", edtHorario.getText().toString());
                carSetting.update(auto.getId(), hashmap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    private void LimpiarCajas() {
        edtNombreCliente.setText("");
        edtTelefonoCliente.setText("");
        edtDireccion.setText("");
        edtHorario.setText("");

    }
}