package com.example.athos.cg;


import android.annotation.SuppressLint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
//@SuppressLint("ValidFragment")
public class ItemFragment extends Fragment {

    GLSurfaceView superficieDesenho = null;
    RenderizadorMenu render = null;
    int id;

    public ItemFragment(){}
    @SuppressLint("ValidFragment")
    public ItemFragment(int id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        superficieDesenho = new GLSurfaceView(getContext());
        render  = new RenderizadorMenu(getActivity(), id);
        superficieDesenho.setRenderer(render);
        return superficieDesenho;
    }

}
