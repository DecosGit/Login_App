package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.AdapterClientes;
import com.example.login.Adapter.AdapterEmpleados;
import com.example.login.Adapter.RVAdapterClientes;
import com.example.login.Adapter.RVAdapterEmpleados;
import com.example.login.Dao.DaoClientes;
import com.example.login.Dao.DaoEmpleados;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.ClientesDto;
import com.example.login.Dto.EmpleadosDto;
import com.example.login.Dto.VehiculosDto;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Empleados extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdapterEmpleados adapter;
    RVAdapterEmpleados adapter1;
    DaoEmpleados dao;
    String key = null;
    FirebaseRecyclerAdapter adapEmpleados;

    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Empleados")
                .limitToLast(50);
        mbase = FirebaseDatabase.getInstance().getReference().child("Empleados");
        recyclerView = (RecyclerView) findViewById(R.id.ListaEmpleados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*FirebaseRecyclerOptions<EmpleadosDto> options = new FirebaseRecyclerOptions.Builder<EmpleadosDto>()
                .setQuery(query, EmpleadosDto.class)
                .build();
                */
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter1 = new RVAdapterEmpleados(this);
        recyclerView.setAdapter(adapter1);
        //adapter = new AdapterEmpleados(options);
        // Connecting Adapter class with the Recycler view*/
       // recyclerView.setAdapter(adapter);

        dao = new DaoEmpleados();
        loadData();

    }
    private void loadData() {
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<EmpleadosDto> listas = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    EmpleadosDto car = data.<EmpleadosDto>getValue(EmpleadosDto.class);
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
    public void NuevoEmpleado(View view) {
        Intent intent = new Intent(this, NewEmp_Activity.class);
        startActivity(intent);
    }


}