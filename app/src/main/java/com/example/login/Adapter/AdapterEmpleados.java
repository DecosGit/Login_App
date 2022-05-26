package com.example.login.Adapter;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Dto.EmpleadosDto;
import com.example.login.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdapterEmpleados extends FirebaseRecyclerAdapter<
        EmpleadosDto, AdapterEmpleados.EmpleadoViewholder> {

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Empleados")
            .limitToLast(50);

    public AdapterEmpleados(
            @NonNull FirebaseRecyclerOptions<EmpleadosDto> options)

    {
        super(options);
    }


    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull EmpleadoViewholder holder,
                     int position, @NonNull EmpleadosDto model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.nombre.setText(model.getNombreCompleto());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.telefono.setText(model.getTelefono());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.correo.setText(model.getCorreo());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.contraseña.setText(model.getContraseña());
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public EmpleadoViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_empleados, parent, false);
        return new AdapterEmpleados.EmpleadoViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class EmpleadoViewholder
            extends RecyclerView.ViewHolder {
        TextView nombre, telefono, correo, contraseña;
        public EmpleadoViewholder(@NonNull View itemView)
        {
            super(itemView);
            nombre= itemView.findViewById(R.id.name_Empl);
            telefono = itemView.findViewById(R.id.phoneEmp);
            correo = itemView.findViewById(R.id.emailAdress);
            contraseña = itemView.findViewById(R.id.ContraseñaEmp);
        }
    }
}
