package com.example.login;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.Dao.DaoEnvios;
import com.example.login.Dto.EnviosDto;
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

public class NewEnvio extends AppCompatActivity {


    Spinner boxEnvioCliente, boxEnvioVehiculo, boxEmpleado, boxEntrega;
    Button ModificarCar, Guardar;
    DaoEnvios carSetting;
    String keyVehiculo="";

    FirebaseDatabase firebase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_envio);

        boxEnvioCliente = (Spinner) findViewById(R.id.boxCliente);
        boxEnvioVehiculo = (Spinner) findViewById(R.id.boxVehiculo);
        boxEmpleado = (Spinner) findViewById(R.id.boxEmpleado);
        boxEntrega = (Spinner) findViewById(R.id.boxEntregado);
        Guardar = (Button) findViewById(R.id.GuardarEnvio);

        inicializarFirebase();
        /*EnviosDto car = (EnviosDto) getIntent().getSerializableExtra("Edit");
        if(car!=null){
            Guardar.setText("Modificar");

            boxEnvioCliente.setS(car.getPlaca());
            inpMarca.setText(car.getMarca());
            inpColor.setText(car.getColor());
            inpCombustible.setText(car.getCombustible());
        }else{
            Guardar.setText("Submit");
        }*/

       llamarClientes();
       llamarVehiculos();
       llamarEmpleados();
       llamarEstados();


    }

    private void llamarEstados() {
        ArrayList<String> vehiculos = getEstadoList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(NewEnvio.this, android.R.layout.simple_spinner_dropdown_item, vehiculos);
        boxEntrega.setAdapter(adapter);
    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
       // firebase.setPersistenceEnabled(true);
        databaseReference = firebase.getReference();
    }

    public void Nuevo(View view) {
        validacion();

        finish();
    }

    private void validacion(){

        inicializarFirebase();
        String combustibles = boxEntrega.getSelectedItem().toString();
        String Cliente = boxEnvioCliente.getSelectedItem().toString();
        String vehiculo = boxEnvioVehiculo.getSelectedItem().toString();
        String empleado = boxEmpleado.getSelectedItem().toString();


        if(Cliente.equals("")){
            Toast.makeText(this, "Seleccionar Cliente", Toast.LENGTH_SHORT).show();
        }
        if(vehiculo.equals("")){
            Toast.makeText(this, "Seleccionar Vehiculo", Toast.LENGTH_SHORT).show();
        }
        if(empleado.equals("")){
            Toast.makeText(this, "Seleccionar Empleado", Toast.LENGTH_SHORT).show();
        }
        if(combustibles.equals("")){
            Toast.makeText(this, "Seleccionar Estado de entrega", Toast.LENGTH_SHORT).show();
        }else{
            EnviosDto car = new EnviosDto();
            if(car!=null) {
                car.setId(UUID.randomUUID().toString());
                car.setNombre_Cliente(Cliente);
                car.setVehiculo(vehiculo);
                car.setRepartidor(empleado);
                car.setEstado_entrega(combustibles);
                String mytime = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
                car.setHora_entrega(mytime);
                databaseReference.child("Envios").child(car.getId()).setValue(car);
                Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
            }else{ //este metodo es para modificar Envios
                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("id", car.getId());
                hashmap.put("nombre_Cliente", boxEnvioCliente.getSelectedItem().toString());
                hashmap.put("vehiculo", boxEnvioVehiculo.getSelectedItem().toString());
                hashmap.put("repartidor", boxEmpleado.getSelectedItem().toString());
                hashmap.put("estado_entrega", boxEntrega.getSelectedItem().toString());
                carSetting.update(car.getId(), hashmap).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();
                    finish();
                }).addOnFailureListener(er ->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }

        }
    }


    public void llamarClientes(){

        DatabaseReference ref;
        ref = firebase.getReference("Clientes");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> clientes = new ArrayList<>();

                    for (DataSnapshot help: snapshot.getChildren()) {
                        String nombre = (String) help.child("nombreCliente").getValue();
                        clientes.add(nombre);
                    }
                    ArrayAdapter<String> arrayClientes = new ArrayAdapter<>(NewEnvio.this, android.R.layout.simple_spinner_dropdown_item, clientes);
                    boxEnvioCliente.setAdapter(arrayClientes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void llamarEmpleados(){

        DatabaseReference ref;
        ref = firebase.getReference("Empleados");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> empleado = new ArrayList<>();

                for (DataSnapshot help: snapshot.getChildren()) {
                    String nombre = (String) help.child("nombreCompleto").getValue();
                    empleado.add(nombre);
                }
                ArrayAdapter<String> arrayEmpleado = new ArrayAdapter<>(NewEnvio.this, android.R.layout.simple_spinner_dropdown_item, empleado);
                boxEmpleado.setAdapter(arrayEmpleado);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<String> getEstadoList(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Pendiente");
        lista.add("Faltantes");
        lista.add("Entregado");

        return lista;
    }


    public void llamarVehiculos(){

        DatabaseReference ref;
        ref = firebase.getReference("Vehiculos");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> vehiculo = new ArrayList<>();

                for (DataSnapshot help: snapshot.getChildren()) {
                    String id = help.getKey();
                    String nombre = (String) help.child("marca").getValue();
                    vehiculo.add(nombre);
                }
                ArrayAdapter<String> arrayVehiculos = new ArrayAdapter<>(NewEnvio.this, android.R.layout.simple_spinner_dropdown_item, vehiculo);
                boxEnvioVehiculo.setAdapter(arrayVehiculos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}