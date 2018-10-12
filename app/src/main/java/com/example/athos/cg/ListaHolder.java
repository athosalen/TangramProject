package com.example.athos.cg;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListaHolder extends RecyclerView.ViewHolder {

    private TextView id;
    private TextView nome;

    public ListaHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        nome = itemView.findViewById(R.id.cell_text);
    }
    public TextView getId(){
        return id;
    }
    public TextView getNome() {
        return nome;
    }
}
