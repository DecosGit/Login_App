package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.login.Adapter.AdapterClientes;
import com.example.login.Adapter.RVAdapterClientes;
import com.example.login.Adapter.RVAdapterVehiculos;
import com.example.login.Dao.DaoClientes;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.ClientesDto;
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

public class Clientes_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdapterClientes adapter;
    RVAdapterClientes adapter1;
    DaoClientes dao;
    String key = null;
    FirebaseRecyclerAdapter adapClientes;

    // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Clientes")
                .limitToLast(50);
        mbase = FirebaseDatabase.getInstance().getReference().child("Clientes");
        recyclerView = (RecyclerView) findViewById(R.id.ListaClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* FirebaseRecyclerOptions<ClientesDto> options = new FirebaseRecyclerOptions.Builder<ClientesDto>()
                .setQuery(query, ClientesDto.class)
                .build();

                */
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter1 = new RVAdapterClientes(this);
        recyclerView.setAdapter(adapter1);
        //adapter = new AdapterClientes(options);
        // Connecting Adapter class with the Recycler view*/
        //recyclerView.setAdapter(adapter);

        dao = new DaoClientes();
        loadData();
    }
    private void loadData() {
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ClientesDto> listas = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    ClientesDto car = data.<ClientesDto>getValue(ClientesDto.class);
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
/*
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
*/
    public void NuevoCliente(View view) {
        Intent intent = new Intent(this, NewCliente_Activity.class);
        startActivity(intent);
    }
}