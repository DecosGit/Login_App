package com.example.login.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Dao.DaoEmpleados;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.EmpleadosDto;
import com.example.login.Dto.VehiculosDto;
import com.example.login.NewAuto;
import com.example.login.NewEmp_Activity;
import com.example.login.R;

import java.util.ArrayList;

public class RVAdapterEmpleados extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final Context mContext;
    ArrayList<EmpleadosDto> listaEmpleados = new ArrayList<>();

    public RVAdapterEmpleados(Context ctx){
        this.mContext = ctx;
    }

    public void setItems(ArrayList<EmpleadosDto> lista){
        listaEmpleados.addAll(lista);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.recycler_empleados, parent, false);
        return new EmpleadosVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EmpleadosVH vh = (EmpleadosVH) holder;
        EmpleadosDto car =listaEmpleados.get(position);
        vh.NombreCliente.setText(car.getNombreCompleto());
        vh.TelefonoCliente.setText(car.getTelefono());
        vh.CorreoCliente.setText(car.getCorreo());
        vh.ContraseñaCliente.setText(car.getContraseña());
        vh.optionEmp.setOnClickListener(v ->{
            PopupMenu popupMenu = new PopupMenu(mContext, vh.optionEmp);
            popupMenu.inflate(R.menu.menu_delete_update);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.menu_edit:

                        Intent intent =new Intent(mContext, NewEmp_Activity.class);
                        intent.putExtra("Edit", car);
                        mContext.startActivity(intent);

                        break;
                    case R.id.menu_delete:
                        DaoEmpleados dao = new DaoEmpleados();
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
        return listaEmpleados.size();
    }
}
