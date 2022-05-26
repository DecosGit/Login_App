package com.example.login.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Dto.EnviosDto;
import com.example.login.Dto.VehiculosDto;
import com.example.login.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdapterEnvios extends FirebaseRecyclerAdapter<
        EnviosDto, AdapterEnvios.EnviosViewholder> {

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("Envios")
            .limitToLast(50);

    public AdapterEnvios(
            @NonNull FirebaseRecyclerOptions<EnviosDto> options)

    {
        super(options);
    }


    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull AdapterEnvios.EnviosViewholder holder,
                                    int position, @NonNull EnviosDto model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.nombre_cliente.setText(model.getNombre_Cliente());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.vehiculo.setText(model.getVehiculo());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.empleadoRepartidor.setText(model.getRepartidor());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.estado_entrega.setText(model.getEstado_entrega());
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public AdapterEnvios.EnviosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.envios_recycler, parent, false);
        return new AdapterEnvios.EnviosViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class EnviosViewholder
            extends RecyclerView.ViewHolder {
        TextView nombre_cliente, vehiculo, empleadoRepartidor, estado_entrega;
        public EnviosViewholder(@NonNull View itemView)
        {
            super(itemView);
            nombre_cliente= itemView.findViewById(R.id.name_cliente);
            vehiculo = itemView.findViewById(R.id.vehiculo_envio);
            empleadoRepartidor = itemView.findViewById(R.id.empleado_repartidor);
            estado_entrega = itemView.findViewById(R.id.estado);
        }
    }

}
