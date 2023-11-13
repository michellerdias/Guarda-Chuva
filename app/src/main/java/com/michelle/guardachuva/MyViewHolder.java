package com.michelle.guardachuva;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nomeView,cpfView, ruaView, volumeView, alturaView, anguloView, cepView, larguraView, numeroView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        cpfView = itemView.findViewById(R.id.cpf);
        nomeView = itemView.findViewById(R.id.nomeProprietario);
        ruaView = itemView.findViewById(R.id.rua);
        volumeView = itemView.findViewById(R.id.volume);
        alturaView = itemView.findViewById(R.id.altura);
        anguloView = itemView.findViewById(R.id.angulo);
        cepView = itemView.findViewById(R.id.cep);
        larguraView = itemView.findViewById(R.id.largura);
        numeroView = itemView.findViewById(R.id.numero);

    }
}
