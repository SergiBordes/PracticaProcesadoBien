package com.example.advaepe.practicaprocesado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class adaptadorRecycler extends RecyclerView.Adapter<adaptadorRecycler.ViewHolder> {

    private List<Habitacion> items;
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, temperatura;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            temperatura = itemView.findViewById(R.id.temperatura);

        }


    }

    public adaptadorRecycler(List<Habitacion> items) {
        this.items = items;
    }
    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// Inflamos la vista desde el xml
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.lista_recycler, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }
    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
       holder.nombre.setText(items.get(posicion).getNombre());
        holder.temperatura.setText(items.get(posicion).getTemperatura());
    }
    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return items.size();
    }

    protected View.OnClickListener onClickListener;
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}