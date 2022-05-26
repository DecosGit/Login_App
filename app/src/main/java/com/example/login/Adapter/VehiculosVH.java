package com.example.login.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.login.R;

public class VehiculosVH extends RecyclerView.ViewHolder {

    TextView placa, marca, color, combustible, option;

    public VehiculosVH(@NonNull View itemView) {
        super(itemView);
        placa= itemView.findViewById(R.id.tvplaca);
        marca = itemView.findViewById(R.id.tvmarca);
        color = itemView.findViewById(R.id.tvcolor);
        combustible = itemView.findViewById(R.id.tvcombustible);
        option = itemView.findViewById(R.id.option);


    }
}
