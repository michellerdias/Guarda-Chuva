package com.michelle.guardachuva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Microbacia> items;

    public MyAdapter(Context context, List<Microbacia> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.microbacia_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.nomeView.setText(items.get(position).getNomeProprietario());
        holder.cpfView.setText(items.get(position).getCpf());
        holder.ruaView.setText(items.get(position).getRua());
        holder.volumeView.setText(items.get(position).getVolume());
        holder.alturaView.setText(items.get(position).getAltura());
        holder.anguloView.setText(items.get(position).getAngulo());
        holder.cepView.setText(items.get(position).getCep());
        holder.larguraView.setText(items.get(position).getLargura());
        holder.numeroView.setText(items.get(position).getNumero());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
