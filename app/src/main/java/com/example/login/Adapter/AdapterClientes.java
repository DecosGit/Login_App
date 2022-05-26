package com.example.login.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Dto.ClientesDto;
import com.example.login.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View


public class AdapterClientes extends FirebaseRecyclerAdapter<
        ClientesDto, AdapterClientes.ClienteViewholder> {

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Clientes")
            .limitToLast(50);

    public AdapterClientes(
            @NonNull FirebaseRecyclerOptions<ClientesDto> options)

    {
        super(options);
    }


    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull ClienteViewholder holder,
                     int position, @NonNull ClientesDto model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.nombre.setText(model.getNombreCliente());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.telefono.setText(model.getTelefonoCliente());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.direccion.setText(model.getDireccion());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.horario.setText(model.getHorario());
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public ClienteViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_clientes, parent, false);
        return new AdapterClientes.ClienteViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class ClienteViewholder
            extends RecyclerView.ViewHolder {
        TextView nombre, telefono, direccion, horario;
        public ClienteViewholder(@NonNull View itemView)
        {
            super(itemView);
            nombre= itemView.findViewById(R.id.name);
            telefono = itemView.findViewById(R.id.phone);
            direccion = itemView.findViewById(R.id.adress);
            horario = itemView.findViewById(R.id.ultimoCampo);
        }
    }
}