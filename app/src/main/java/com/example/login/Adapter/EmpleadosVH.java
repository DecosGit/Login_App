package com.example.login.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

public class EmpleadosVH extends RecyclerView.ViewHolder {
    TextView NombreCliente, TelefonoCliente, CorreoCliente, ContraseñaCliente, optionEmp;

    public EmpleadosVH(@NonNull View itemView) {
        super(itemView);
        NombreCliente = itemView.findViewById(R.id.name_Empl);
        TelefonoCliente = itemView.findViewById(R.id.phoneEmp);
        CorreoCliente = itemView.findViewById(R.id.emailAdress);
        ContraseñaCliente = itemView.findViewById(R.id.ContraseñaEmp);
        optionEmp = itemView.findViewById(R.id.optionEmp);
    }
}
