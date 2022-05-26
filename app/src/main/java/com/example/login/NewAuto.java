package com.example.login;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.EnviosDto;
import com.example.login.Dto.VehiculosDto;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class NewAuto extends AppCompatActivity {

    TextInputLayout layPlaca, layMarca, layColor, layCombutible;
    EditText inpPlaca, inpMarca, inpColor, inpCombustible;
    Button ModificarCar, Guardar;
    DaoVehiculos carSetting;
    String keyVehiculo="";

    FirebaseDatabase firebase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_auto);

        layPlaca = (TextInputLayout) findViewById(R.id.layPlaca1);
        layMarca = (TextInputLayout) findViewById(R.id.layMarca1);
        layColor = (TextInputLayout) findViewById(R.id.layColor1);
        layCombutible = (TextInputLayout) findViewById(R.id.layCombustible1);
        inpPlaca = (EditText) findViewById(R.id.edtPlaca);
        inpMarca = (EditText) findViewById(R.id.edt_marca);
        inpColor = (EditText) findViewById(R.id.edtcolor);
        inpCombustible = (EditText) findViewById(R.id.edtCombustible_);
        ModificarCar = (Button) findViewById(R.id.btnCarModificar);
        Guardar = (Button) findViewById(R.id.MatbtnGuardarAuto);


        inicializarFirebase();
        VehiculosDto car = (VehiculosDto) getIntent().getSerializableExtra("Edit");
        if(car!=null){
            Guardar.setText("Modificar");

            inpPlaca.setText(car.getPlaca());
            inpMarca.setText(car.getMarca());
            inpColor.setText(car.getColor());
            inpCombustible.setText(car.getCombustible());
        }else{
            Guardar.setText("Submit");
        }



        ModificarCar.setOnClickListener(v->{

            HashMap<String, Object> hashmap = new HashMap<>();
            hashmap.put("placa", inpPlaca.getText().toString());
            hashmap.put("marca", inpMarca.getText().toString());
            hashmap.put("color", inpColor.getText().toString());
            hashmap.put("combustible", inpCombustible.getText().toString());
            carSetting.update(keyVehiculo, hashmap).addOnSuccessListener(suc->{
                Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        });

    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);
        databaseReference = firebase.getReference();
    }

    public void Nuevo(View view) {

        validacion();

        finish();

    }

    private void validacion(){

        inicializarFirebase();

        String placa = inpPlaca.getText().toString();
        String marca = inpMarca.getText().toString();
        String color = inpColor.getText().toString();
        String combustibles = inpCombustible.getText().toString();

        if(placa.equals("")){
            inpPlaca.setError("Requerido");
        }
        if(marca.equals("")){
            inpMarca.setError("Requerido");
        }
        if(color.equals("")){
            inpColor.setError("Requerido");
        }
        if(combustibles.equals("")){
            inpCombustible.setError("Requerido");
        }else{


            VehiculosDto car = new VehiculosDto();

            if(car!=null) {    //este metodo es para guardar vehiculos
                car.setId(UUID.randomUUID().toString());
                car.setPlaca(placa);
                car.setMarca(marca);
                car.setColor(color);
                car.setCombustible(combustibles);
                databaseReference.child("Vehiculos").child(car.getId()).setValue(car);
                Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                LimpiarCajas();
            }else{  //este metodo es para modificar vehiculos
                HashMap<String, Object> hashmap = new HashMap<>();
                hashmap.put("id", car.getId());
                hashmap.put("placa", inpPlaca.getText().toString());
                hashmap.put("marca", inpMarca.getText().toString());
                hashmap.put("color", inpColor.getText().toString());
                hashmap.put("combustible", inpCombustible.getText().toString());
                carSetting.update(car.getId(), hashmap).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();
                    finish();
                }).addOnFailureListener(er ->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    private void LimpiarCajas() {
        inpPlaca.setText("");
        inpMarca.setText("");
        inpColor.setText("");
        inpCombustible.setText("");

    }


}