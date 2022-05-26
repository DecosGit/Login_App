package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.login.Adapter.AdapterEmpleados;
import com.example.login.Adapter.AdapterEnvios;
import com.example.login.Adapter.RVAdapterClientes;
import com.example.login.Adapter.RVAdapterEnvios;
import com.example.login.Dao.DaoClientes;
import com.example.login.Dao.DaoEnvios;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.EmpleadosDto;
import com.example.login.Dto.EnviosDto;
import com.example.login.Dto.VehiculosDto;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Envios_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdapterEnvios adapter;
    RVAdapterEnvios adapter1;
    DaoEnvios dao;
    String key = null;
    FirebaseRecyclerAdapter adapEnvios;

    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Envios")
                .limitToLast(50);
        mbase = FirebaseDatabase.getInstance().getReference().child("Envios");
        recyclerView = (RecyclerView) findViewById(R.id.ListaEvios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<EnviosDto> options = new FirebaseRecyclerOptions.Builder<EnviosDto>()
                .setQuery(query, EnviosDto.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new AdapterEnvios(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        //adapter1 = new RVAdapterEnvios(this);
        //recyclerView.setAdapter(adapter1);
/*
        dao = new DaoEnvios();
        loadData();  */
    }
    /*
    private void loadData() {
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<EnviosDto> listas = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    EnviosDto car = data.<EnviosDto>getValue(EnviosDto.class);
                    car.setId(data.getKey());
                    listas.add(car);
                    key = data.getKey();

                }
                adapter1.setItems(listas);
                adapter1.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    */

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    public void NuevoEnvio(View view) {

        Intent intent = new Intent(this, NewEnvio.class);
        startActivity(intent);

    }
}