package com.example.login.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

public class EnviosVH extends RecyclerView.ViewHolder {
    TextView envCliente, envVehiculo, envEmp, Estado, optionEnvios;


    public EnviosVH(@NonNull View itemView) {
        super(itemView);
        envCliente= itemView.findViewById(R.id.name_cliente);
        envVehiculo = itemView.findViewById(R.id.vehiculo_envio);
        envEmp = itemView.findViewById(R.id.empleado_repartidor);
        Estado = itemView.findViewById(R.id.boxEntregado);
        optionEnvios = itemView.findViewById(R.id.optionEnvios);
    }
}
