package com.example.athos.cg;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ListaHolder> {

    private Context contexto;
    private ArrayList<ItemMenu> itemList;

    public Adapter(Context contexto, ArrayList<ItemMenu> itemList) {
        this.contexto = contexto;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ListaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cell = LayoutInflater.from(contexto).inflate(R.layout.tangram_cell,parent,false);
        ListaHolder holder = new ListaHolder(cell);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaHolder holder, final int position) {
        ItemMenu item = itemList.get(position);

        holder.getId().setText(item.getId());
        holder.getNome().setText(item.getNome());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
