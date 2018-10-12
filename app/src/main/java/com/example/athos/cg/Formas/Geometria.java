package com.example.athos.cg.Formas;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public abstract class Geometria {
    public static final int CENTER = 0;
    public static final int BOTTOM_LEFT = 1;
    public static final int BOTTOM_RIGHT = 2;
    public static final int TOP_LEFT = 3;
    public static final int TOP_RIGHT = 4;
    public static final int BOTTOM = 5;
    public static final int TOP = 6;
    public static final int LEFT = 7;
    public static final int RIGHT = 8;

    private float width, height;
    private float x, y;
    private float scale;
    private float[] coords;

    private float translatefX, translatefY;
    private float red, green, blue, alpha;
    private int angleX, angleY,angleZ;
    private int relativeTo;

    public Geometria() {
        this.translatefX = 0;
        this.translatefY = 0;
        this.red = (float)Math.random();
        this.green = (float)Math.random();
        this.blue = (float)Math.random();
        this.alpha = 1;
        this.angleX = 0;
        this.angleY = 0;
        this.angleZ = 0;
        this.scale = 1;
    }
    protected Geometria(float width, float height, float x, float y){
        this();
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.relativeTo = CENTER;
    }
    protected FloatBuffer criaNIOBuffer(float[] coordenadas){
        //Aloca qtd de bytes necessarias para armazenar os dados dos vertices
        ByteBuffer vetBytes = ByteBuffer.allocateDirect(coordenadas.length * 4);
        //Usa o sistema de enderecamento de memoria nativo do processador em questao
        vetBytes.order(ByteOrder.nativeOrder());
        //Cria o FloatBuffer a partir do byteBuffer
        FloatBuffer buffer = vetBytes.asFloatBuffer();
        //Limpa um eventual lixo de memoria
        buffer.clear();
        //Encapsulo o Array Java no objeto FloatBuffer
        buffer.put(coordenadas);
        //Corta o excesso da memoria que nao esta sendo utilizado
        buffer.flip();
        //Retorna o objeto de coordenadas
        return buffer;
    }

    protected void createTranslatef(){
        switch (relativeTo){
            default:
                translatefX = x + width/2;
                translatefY = y + height/2;
                break;
            case CENTER:
                translatefX = x;
                translatefY = y;
                break;
            case BOTTOM_LEFT:
                translatefX = x + width/2;
                translatefY = y + height/2;
                break;
            case BOTTOM_RIGHT:
                translatefX = x - width/2;
                translatefY = y + height/2;
                break;
            case TOP_LEFT:
                translatefX = x + width/2;
                translatefY = y - height/2;
                break;
            case TOP_RIGHT:
                translatefX = x - width/2;
                translatefY = y - height/2;
                break;
            case BOTTOM:
                translatefX = x;
                translatefY = y + height/2;
                break;
            case TOP:
                translatefX = x;
                translatefY = y - height/2;
                break;
            case LEFT:
                translatefX = x + width/2;
                translatefY = y;
                break;
            case RIGHT:
                translatefX= x - width/2;
                translatefY = y;
        }
    }

    public void desenha(GL10 gl) {
        gl.glLoadIdentity();
        FloatBuffer buffer = criaNIOBuffer(coords);
        gl.glVertexPointer(2, gl.GL_FLOAT, 0, buffer);
        gl.glColor4f(red, green, blue, alpha);
        gl.glTranslatef(translatefX, translatefY, 0);
        gl.glRotatef(angleX, 1, 0, 0);
        gl.glRotatef(angleY, 0, 1, 0);
        gl.glRotatef(angleZ, 0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0, coords.length/2);

        gl.glColor4f(0,0,0,1);

        for(int i=0;; i++){
            if( i+2 == coords.length/2)
                break;
            gl.glDrawArrays(GL10.GL_LINE_LOOP,i,3);
        }
    }

    public Geometria rotateX(){
        angleX = (angleX == 0) ? 180 : 0;
        return this;
    }

    public Geometria rotateY(){
        angleY = (angleY == 0) ? 180 : 0;
        return this;
    }
    public Geometria rotateY(boolean rotate){
        if (rotate)
            angleY = (angleY == 0) ? 180 : 0;
        return this;
    }
    public boolean getRotateY() {
        return (angleY == 180)?true:false;
    }

    public Geometria rotateZ(){
        if(this.angleZ == 0)
            this.angleZ = 315;
        else
            this.angleZ -= 45;
        return this;
    }
    public Geometria rotateZ(int angleZ){
        this.angleZ = angleZ;
        return this;
    }



    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRelativeTo() {
        return relativeTo;
    }

    public float[] getCoords() {
        return coords;
    }

    public float getAlpha() {
        return alpha;
    }

    public float getTranslatefX() {
        return translatefX;
    }

    public float getTranslatefY() {
        return translatefY;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public int getAngleZ() {
        return angleZ;
    }

    public float getScale(){
        return scale;
    }

    public Geometria setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public Geometria setColor(float red, float green, float blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    protected void setCoords(float[] coords){
        this.coords = coords;
    }

    public Geometria setAnchorPoint(int anchorPoint){
        relativeTo = anchorPoint;
        createTranslatef();
        return this;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void resize(float scale){
        this.scale = scale;
        setCoords(coords());
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
        createTranslatef();
    }

    abstract float[] coords();
}
