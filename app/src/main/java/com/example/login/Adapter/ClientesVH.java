package com.example.login.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.login.R;

public class ClientesVH extends RecyclerView.ViewHolder {
    TextView NombreCliente, PhoneCliente, Direccion, Horario, optionCliente;

    public ClientesVH(@NonNull View itemView) {
        super(itemView);
        NombreCliente= itemView.findViewById(R.id.name);
        PhoneCliente = itemView.findViewById(R.id.phone);
        Direccion = itemView.findViewById(R.id.adress);
        Horario = itemView.findViewById(R.id.ultimoCampo);
        optionCliente = itemView.findViewById(R.id.optionClient);
    }
}
