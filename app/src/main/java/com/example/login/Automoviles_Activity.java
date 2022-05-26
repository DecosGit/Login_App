package com.example.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Adapter.AdapterVehiculos;
import com.example.login.Adapter.RVAdapterVehiculos;
import com.example.login.Adapter.VehiculosVH;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.VehiculosDto;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Automoviles_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdapterVehiculos adapter;
    RVAdapterVehiculos adapter1;
    DatabaseReference mbase;
    DaoVehiculos dao;
    String key = null;
    FirebaseRecyclerAdapter adapVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automoviles);
        this.setTitle("Automoviles");

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Vehiculos")
                .limitToLast(50);


        mbase = FirebaseDatabase.getInstance().getReference().child("Vehiculos");
        recyclerView = (RecyclerView) findViewById(R.id.ListaVehiculos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
        FirebaseRecyclerOptions<VehiculosDto> options = new FirebaseRecyclerOptions.Builder<VehiculosDto>()
                .setQuery(dao.get(), new SnapshotParser<VehiculosDto>() {
                    @NonNull
                    @Override
                    public VehiculosDto parseSnapshot(@NonNull DataSnapshot snapshot) {
                        VehiculosDto car = snapshot.getValue(VehiculosDto.class);
                        car.setId(snapshot.getKey());

                        return car;
                    }
                })
                .build();

        adapVehiculos = new FirebaseRecyclerAdapter(options){

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(Automoviles_Activity.this).inflate(R.layout.recycler_vehiculos, parent, false);
                return new VehiculosVH(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {
                VehiculosVH vh = (VehiculosVH) holder;
                VehiculosDto car = (VehiculosDto)model;
                holder.placa.setText(car.getPlaca());
                vh.marca.setText(car.getMarca());
                vh.color.setText(car.getColor());
                vh.combustible.setText(car.getCombustible());
                vh.option.setOnClickListener(v ->{
                    PopupMenu popupMenu = new PopupMenu(Automoviles_Activity.this, vh.option);
                    popupMenu.inflate(R.menu.menu_delete_update);
                    popupMenu.setOnMenuItemClickListener(item -> {
                        switch (item.getItemId()){
                            case R.id.menu_edit:

                                Intent intent =new Intent(Automoviles_Activity.this, NewAuto.class);
                                intent.putExtra("Edit", car);
                                startActivity(intent);

                                break;
                            case R.id.menu_delete:
                                DaoVehiculos dao = new DaoVehiculos();
                                dao.delete(car.getId()).addOnSuccessListener(suc ->{
                                    Toast.makeText(Automoviles_Activity.this, "registro eliminado", Toast.LENGTH_SHORT).show();
                                   // notifyItemRemoved(position);
                                }).addOnFailureListener(er ->{
                                    Toast.makeText(Automoviles_Activity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                                break;

                        }
                        return false;
                    });

                    popupMenu.show();
                });
            }
        }

        */

        // Connecting object of required Adapter class to
        // the Adapter class itself

        //adapter = new AdapterVehiculos(options);

        adapter1 = new RVAdapterVehiculos(this);
        // Connecting Adapter class with the Recycler view*/
       // recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter1);


        dao = new DaoVehiculos();
        loadData();

    }

    private void loadData() {
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<VehiculosDto> listas = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    VehiculosDto car = data.getValue(VehiculosDto.class);
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
    public void NuevoAuto(View view) {
        Intent intent = new Intent(this, NewAuto.class);
        startActivity(intent);
    }
}