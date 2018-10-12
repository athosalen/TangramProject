package com.example.athos.cg;


import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TangramFragment extends Fragment {

    GLSurfaceView superficieDesenho = null;
    Renderizador render = null;

    public TangramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Valida a var de referencia para a superficie
        superficieDesenho = new GLSurfaceView(getContext());

        //valida a variavel de refenrencia para o renderizador
        render  = new Renderizador(getActivity());

        //Associa o renderizador a superficie de desenho
        superficieDesenho.setRenderer(render);

        superficieDesenho.setOnTouchListener(render);

        // Inflate the layout for this fragment
        return superficieDesenho;


    }

}
