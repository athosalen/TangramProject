package com.example.athos.cg.Formas;

public class Quadrado extends Geometria {

    public Quadrado(float width, float height, float x, float y) {
        super(width, height, x, y);
        resize(1);
        createTranslatef();
    }

    public Quadrado(float length, float x, float y){
        this(length, length, x, y);
    }

    @Override
    float[] coords() {
        return new float[]{
                (-getWidth() / 2)*getScale(), (-getHeight() / 2)*getScale(),
                (getWidth() / 2)*getScale(), (-getHeight() / 2)*getScale(),
                (-getWidth() / 2)*getScale(), (getHeight() / 2)*getScale(),
                (getWidth() / 2)*getScale(), (getHeight() / 2)*getScale()};
    }
}
