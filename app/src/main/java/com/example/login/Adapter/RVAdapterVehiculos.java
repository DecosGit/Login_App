package com.example.login.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.VehiculosDto;
import com.example.login.NewAuto;
import com.example.login.R;
import android.content.Context;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;



public class RVAdapterVehiculos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    ArrayList<VehiculosDto> listaVehiculos = new ArrayList<>();

    public RVAdapterVehiculos(Context ctx){
        this.mContext = ctx;
    }

    public void setItems(ArrayList<VehiculosDto> lista){
        listaVehiculos.addAll(lista);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.recycler_vehiculos, parent, false);
        return new VehiculosVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VehiculosVH vh = (VehiculosVH) holder;
        VehiculosDto car =listaVehiculos.get(position);
        vh.placa.setText(car.getPlaca());
        vh.marca.setText(car.getMarca());
        vh.color.setText(car.getColor());
        vh.combustible.setText(car.getCombustible());
        vh.option.setOnClickListener(v ->{
            PopupMenu popupMenu = new PopupMenu(mContext, vh.option);
            popupMenu.inflate(R.menu.menu_delete_update);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.menu_edit:

                        Intent intent =new Intent(mContext, NewAuto.class);
                        intent.putExtra("Edit", car);
                        mContext.startActivity(intent);

                        break;
                    case R.id.menu_delete:
                        DaoVehiculos dao = new DaoVehiculos();
                        dao.delete(car.getId()).addOnSuccessListener(suc ->{
                            Toast.makeText(mContext, "registro eliminado", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er ->{
                            Toast.makeText(mContext, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;

                }
                return false;
            });

            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }
}
