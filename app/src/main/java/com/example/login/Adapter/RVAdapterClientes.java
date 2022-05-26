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

import com.example.login.Dao.DaoClientes;
import com.example.login.Dao.DaoVehiculos;
import com.example.login.Dto.ClientesDto;
import com.example.login.Dto.VehiculosDto;
import com.example.login.NewAuto;
import com.example.login.NewCliente_Activity;
import com.example.login.R;

import java.util.ArrayList;

public class RVAdapterClientes extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private final Context mContext;
    ArrayList<ClientesDto> listaClientes = new ArrayList<>();

    public RVAdapterClientes(Context ctx){
        this.mContext = ctx;
    }

    public void setItems(ArrayList<ClientesDto> lista){
        listaClientes.addAll(lista);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(mContext).inflate(R.layout.recycler_clientes, parent, false);
        return new ClientesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ClientesVH vh = (ClientesVH) holder;
        ClientesDto car =listaClientes.get(position);
        vh.NombreCliente.setText(car.getNombreCliente());
        vh.PhoneCliente.setText(car.getTelefonoCliente());
        vh.Direccion.setText(car.getDireccion());
        vh.Horario.setText(car.getHorario());
        vh.optionCliente.setOnClickListener(v ->{
            PopupMenu popupMenu = new PopupMenu(mContext, vh.optionCliente);
            popupMenu.inflate(R.menu.menu_delete_update);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.menu_edit:

                        Intent intent =new Intent(mContext, NewCliente_Activity.class);
                        intent.putExtra("Edit", car);
                        mContext.startActivity(intent);

                        break;
                    case R.id.menu_delete:
                        DaoClientes dao = new DaoClientes();
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
        return listaClientes.size();
    }
}
