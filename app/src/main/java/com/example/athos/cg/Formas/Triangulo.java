package com.example.athos.cg.Formas;

public class Triangulo extends Geometria {
    private float horizontal_displacement;

    public Triangulo(float width, float height, float horizontal_displacement, float x, float y) {
        super(width, height, x, y);
        if(horizontal_displacement > width || horizontal_displacement < 0)
            this.horizontal_displacement = 0;
        else
            this.horizontal_displacement = -width/2 + horizontal_displacement;
        resize(1);
        createTranslatef();
    }
    public Triangulo(float width, float height, float x, float y){
        this(width, height,width/2, x, y);
    }
    public Triangulo(float length, float x, float y) {
        this(length, (float)(length/2*Math.sqrt(3)),length/2, x, y);
    }

    @Override
    float[] coords(){

        return new float[]{
                (-getWidth() / 2)*getScale(), (-getHeight() / 2)*getScale(),
                (getWidth() / 2)*getScale(), (-getHeight() / 2)*getScale(),
                (horizontal_displacement)*getScale(), (getHeight() / 2)*getScale()};
    }
}
