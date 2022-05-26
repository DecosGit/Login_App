package com.example.login.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Automoviles_Activity;
import com.example.login.Dto.VehiculosDto;
import com.example.login.NewAuto;
import com.example.login.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class AdapterVehiculos extends FirebaseRecyclerAdapter<
        VehiculosDto, AdapterVehiculos.VehiculosViewholder> {

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Vehiculos")
            .limitToLast(50);

    public AdapterVehiculos(
            @NonNull FirebaseRecyclerOptions<VehiculosDto> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull VehiculosViewholder holder,
                     int position, @NonNull VehiculosDto model)
    {



        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.placa.setText(model.getPlaca());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.marca.setText(model.getMarca());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.color.setText(model.getColor());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.combustible.setText(model.getCombustible());
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public VehiculosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_vehiculos, parent, false);
        return new AdapterVehiculos.VehiculosViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class VehiculosViewholder
            extends RecyclerView.ViewHolder {
        TextView placa, marca, color, combustible, option;



        public VehiculosViewholder(@NonNull View itemView)
        {
            super(itemView);
            placa= itemView.findViewById(R.id.tvplaca);
            marca = itemView.findViewById(R.id.tvmarca);
            color = itemView.findViewById(R.id.tvcolor);
            combustible = itemView.findViewById(R.id.tvcombustible);
            option = itemView.findViewById(R.id.option);

        }
    }

}
