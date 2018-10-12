package com.example.athos.cg.Formas;

public class Paralelepipedo extends Geometria {
    private float horizontal_displacement;

    public Paralelepipedo(float width, float height, float horizontal_displacement, float x, float y) {
        super(width, height, x, y);
        if(horizontal_displacement > width/3 || horizontal_displacement <= 0)
            this.horizontal_displacement = width/3;
        else
            this.horizontal_displacement = horizontal_displacement;
        resize(1);
        createTranslatef();
    }
    public Paralelepipedo(float width, float height, float x, float y){
        this(width, height, width/3, x, y);
    }
    public Paralelepipedo(float lenght, float x, float y) {
        this(lenght, lenght/3, lenght/3, x, y);
    }

    @Override
    float[] coords(){

        return new float[]{
                (-getWidth()/2)*getScale(), (-getHeight()/2)*getScale(),
                (-getWidth()/2 + horizontal_displacement + getWidth()/3)*getScale(), (-getHeight()/2)*getScale(),
                (-getWidth()/2 + horizontal_displacement)*getScale(), (getHeight()/2)*getScale(),
                (getWidth()/2)*getScale(), (getHeight()/2)*getScale()};
    }
}
