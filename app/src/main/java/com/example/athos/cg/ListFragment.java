package com.example.athos.cg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ArrayList<ItemMenu> itens;
    public Adapter adapter;
    public RecyclerView recycler;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        itens = new ArrayList<>();
        for (int i = 0;;i++){
            if (PersistenceManager.getValueFloat( getActivity(),i) == null)
                break;
            itens.add(new ItemMenu(i+"", PersistenceManager.getValueString(getActivity(), i)));
        }


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        adapter = new Adapter(getContext(),itens);
        recycler = view.findViewById(R.id.recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);

        return view;
    }


}
