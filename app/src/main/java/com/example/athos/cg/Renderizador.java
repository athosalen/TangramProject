package com.example.athos.cg;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.athos.cg.Formas.Geometria;
import com.example.athos.cg.Formas.Paralelepipedo;
import com.example.athos.cg.Formas.Quadrado;
import com.example.athos.cg.Formas.Triangulo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class Renderizador implements GLSurfaceView.Renderer, View.OnTouchListener, SensorEventListener {
    public static final int QUADRADO = 1;
    public static final int TRIANGULO = 2;
    public static final int PARALELEPIPEDO = 3;

    Activity activity;
    boolean selecionado = false;
    int formaSelecionada;
    int formaIndice = 9;
    float tamanho;
    int selecao = 0;

    float difX;
    float difY;
    //    long tempo = 0;
//    int fps = 0;
    float posX = 300;
    int direcaoX = 1;
    float posY = 300;
    int direcaoY = 1;
    int angulo = 0;

    int width = 0;
    int height = 0;


    ArrayList<Geometria> formas = new ArrayList<>();


    ArrayList<float[]> desenhos = new ArrayList<>();
    FloatBuffer buffer;

    public Renderizador(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //Chamado uma vez quando a superficie de desenho for criada
        //tempo = System.currentTimeMillis();
        //Configura a cor de limpeza no formato RGBA
        gl.glClearColor(1.0f,1.0f, 1.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.width = width;
        this.height = height;
        configuraArea(gl,width,height);

        //Cria o NIOBuffer
        //buffer = criaNIOBuffer(carregaVetor(width,height));
        //gl.glVertexPointer(2, gl.GL_FLOAT, 0, buffer);
        //buffer2 = criaNIOBuffer(vetorjAVA2);

        //Solicita OpenGL permissao para usar vetores de vertices
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        //Chamado quando a superficide de desenho for alterada
        Log.i("INFO",width + " " + height);

        tamanho = width*0.1f;

        formas.add(new Quadrado(tamanho*1.5f, tamanho*0.75f, height-tamanho*0.75f).setAlpha(0));
        formas.add(new Triangulo(tamanho*1.3f, tamanho*0.4f, tamanho*0.75f, height-tamanho*0.375f));
        formas.add(new Quadrado(tamanho*1.3f, tamanho*0.15f, tamanho*0.75f, height-tamanho*0.75f));
        formas.add(new Triangulo(tamanho*1.3f, tamanho*0.4f, tamanho*0.75f, height-tamanho*1.125f).rotateZ(180));

        formas.add(new Quadrado(tamanho*1.5f, width-tamanho*0.75f, height-tamanho*0.75f).setAlpha(0));
        formas.add(new Triangulo(tamanho*1.1f, width-tamanho*0.75f, height-tamanho*0.75f).rotateZ(270));

        formas.add(new Quadrado(tamanho*1.5f, width*0.285f, height-tamanho*0.75f));
        formas.add(new Quadrado(tamanho*1.5f, width*0.5f, height-tamanho*0.75f));
//        formas.add(new Quadrado(tamanho*1.5f, width*0.5f, height*0.5f).setAnchorPoint(Geometria.BOTTOM_LEFT));
        formas.add(new Quadrado(tamanho*1.5f, width*0.715f, height-tamanho*0.75f));

        desenhos.add(new float[]{
                tamanho*2.7f, tamanho*1.3f, 0,
                tamanho*3.5f, tamanho*3.3f, 0,
                tamanho*4.2f, tamanho*1.3f, 90,
                tamanho*3.7f, tamanho*1.8f, 180,
                tamanho*1.2f, tamanho*1.8f, 45,
                tamanho*2.2f, tamanho*4.4f, 0,
                tamanho*1.5f, tamanho*3f, 315, 1});
        desenhos.add(new float[]{
                tamanho, tamanho*2, 270,
                tamanho*2, tamanho*3, 180,
                tamanho*3.5f, tamanho*3, 90,
                tamanho*2, tamanho*1.5f, 0,
                tamanho*3.5f, tamanho*0.5f, 225,
                tamanho*3, tamanho*2, 45,
                tamanho*1.5f, tamanho*0.5f, 0, 0});
        desenhos.add(new float[]{
                tamanho*2f, tamanho*2.2f, 0,
                tamanho*4f, tamanho*2.2f, 180,
                tamanho*5f, tamanho*0.5f, 0,
                tamanho*7.3f, tamanho*2.2f, 270,
                tamanho*6.1f, tamanho*2.6f, 90,
                tamanho*0.5f, tamanho*0.5f, 0,
                tamanho*4.7f, tamanho*1.2f, 45, 0});
        desenhos.add(new float[]{
                tamanho*3.8f, tamanho*0.7f, 135,
                tamanho*4.1f, tamanho*2.8f, 90,
                tamanho*5.6f, tamanho*5.8f, 270,
                tamanho*6.6f, tamanho*5.8f, 90,
                tamanho*5.8f, tamanho*3.4f, 270,
                tamanho*6.1f, tamanho*4.8f, 45,
                tamanho*1.5f, tamanho*0.5f, 0, 1});
        for (int i = 0;;i++){
            float[] x = PersistenceManager.getValueFloat(activity,i);
            if (x == null)
                break;
            desenhos.add(x);
        }


        desenhaFormas(0, 9);

//        formas.add(new Quadrado(tamanho+20, (width/2-tamanho)/2, height*0.9f));
//        formas.add(new Quadrado(tamanho, (width/2-tamanho)/2, height*0.9f));
//        formas.add(new Triangulo(tamanho*2/1.3f, tamanho/1.3f, width*0.47f, height*0.9f));
//        formas.add(new Paralelepipedo(tamanho*3/1.5f, tamanho/1.5f, width-(width/2-tamanho)/2, height*0.9f));
//        formaSelecionada = QUADRADO;

//        formas.add(new Triangulo(200, 100, 200, 300));
    }

    private void desenhaFormas(int i, int lastMenu){
        for(int j = formas.size()-1; j >= lastMenu; j--){
            formas.remove(j);
        }
        formas.add(new Triangulo(tamanho*4, tamanho*2, desenhos.get(i)[0], desenhos.get(i)[1]).rotateZ((int)desenhos.get(i)[2]).setColor(0.75f,0,0));
        formas.add(new Triangulo(tamanho*4, tamanho*2, desenhos.get(i)[3], desenhos.get(i)[4]).rotateZ((int)desenhos.get(i)[5]).setColor(0,0.75f,0));
        formas.add(new Triangulo(tamanho*2, tamanho, desenhos.get(i)[6], desenhos.get(i)[7]).rotateZ((int)desenhos.get(i)[8]).setColor(0,0,0.75f));
        formas.add(new Triangulo(tamanho*2, tamanho, desenhos.get(i)[9], desenhos.get(i)[10]).rotateZ((int)desenhos.get(i)[11]).setColor(0.75f,0.75f,0));
        formas.add(new Triangulo((float)Math.sqrt(Math.pow(tamanho*2, 2)+Math.pow(tamanho*2, 2)),(float)Math.sqrt(Math.pow(tamanho*2, 2)+Math.pow(tamanho*2, 2))/2, desenhos.get(i)[12], desenhos.get(i)[13]).rotateZ(225).rotateZ((int)desenhos.get(i)[14]).setColor(0,0.75f,0.75f));
        formas.add(new Quadrado((float)Math.sqrt(Math.pow(tamanho, 2)+Math.pow(tamanho, 2)), desenhos.get(i)[15], desenhos.get(i)[16]).rotateZ(45).rotateZ((int)desenhos.get(i)[17]).setColor(0.75f,0,0.75f));
        formas.add(new Paralelepipedo(tamanho*3, tamanho, desenhos.get(i)[18], desenhos.get(i)[19]).rotateZ((int)desenhos.get(i)[20]).rotateY((desenhos.get(i)[21]==1)?true:false).setColor(0.75f, 0.75f, 0.75f));
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //Aplica a cor de limpeza de tela a todos os bits do buffer de cor
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);

        synchronized (this){
            for (Geometria forma: formas) {
                forma.desenha(gl);
            }
        }
    }

    public IntBuffer criaIntBuffer(int[] coordenadas){
        ByteBuffer vetBytes = ByteBuffer.allocateDirect(coordenadas.length * 4);
        vetBytes.order(ByteOrder.nativeOrder());
        IntBuffer buffer = vetBytes.asIntBuffer();
        buffer.clear();
        buffer.put(coordenadas);
        buffer.flip();
        return buffer;
    }
    public FloatBuffer criaNIOBuffer(float[] coordenadas){
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

    void configuraArea(GL10 gl, int width, int height){
        //Configurando a area de coordenadas do plano cartesiano
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();

        //Configurando o volume de renderizacao
        gl.glOrthof(0.0f, width,//width
                0.0f, height,//height
                -1.0f, 1.0f);

        //Configurando a matriz de transformacao geometricas
        //Translacao, rotacao e escala
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        //Configura a area de visualizacao na tela do dispositivo
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            if (event.getAction() == MotionEvent.ACTION_MOVE){
                Log.i("INFO","Action_Move");
                if (selecionado){
                    formas.get(formaIndice).move(event.getX() - difX, height - event.getY() - difY);
//                    }
                }
            }
            else if(event.getAction() == MotionEvent.ACTION_DOWN){
                Log.i("INFO","Action_Down");
                for (int i = formas.size()-1; i >= 0; i--){
                    if(event.getX() > formas.get(i).getX()-formas.get(i).getWidth()/2 &&
                            event.getX() < formas.get(i).getX()+formas.get(i).getWidth()/2 &&
                            height - event.getY() > formas.get(i).getY()-formas.get(i).getHeight()/2 &&
                            height - event.getY() < formas.get(i).getY()+formas.get(i).getHeight()/2){
                        if(i <= 3) {
                            formas.get(formaIndice).rotateY();
                            break;
                        }
                        else if(i<=5) {
                            formas.get(formaIndice).rotateZ();
                            break;
                        }
                        else if(i<=6) {
//                            PersistenceManager.deleteAll(activity);
//                            Intent j = activity.getPackageManager()
//                                    .getLaunchIntentForPackage( activity.getPackageName() );
//                            j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            activity.startActivity(j);
//                            break;

                        }
                        else if(i<=7) {
                            float[] x = new float[22];
                            int k = 9;
                            for (int j = 0; k < formas.size(); j+=3){
                                x[j] = formas.get(k).getX();
                                x[j+1] = formas.get(k).getY();
                                x[j+2] = formas.get(k).getAngleZ();
                                k++;
                            }
                            x[x.length-1] = formas.get(formas.size()-1).getRotateY()?1:0;
                            PersistenceManager.save(activity, x, "tangram");
                            desenhos.add(x);
                            break;
                        }
                        else if(i<=8) {
                            if(desenhos.size()-1 == selecao){
                                selecao = 0;
                            }
                            else selecao++;
                            desenhaFormas(selecao, 9);
                            break;
                        }
                        else{
                            selecionado = true;
                            formaIndice = i;
                            difX = event.getX() - formas.get(i).getX() ;
                            difY = height - event.getY() - formas.get(i).getY();
                            break;
                        }
                    }
                }
//            selecionado = true;
//            formaIndice = 0;
//            difX = event.getX() - formas.get(0).getX() ;
//            difY = height - event.getY() - formas.get(0).getY();
                return true;
            }
            else if (event.getAction() == MotionEvent.ACTION_UP){
                Log.i("INFO","Action_Up");
                selecionado = false;
            }
            return true;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            if(formas.size()>=1){
                float direcaoX = event.values[0]/10*-1;
                float direcaoY = event.values[1]/10*-1;
                if(direcaoX < 0.05 && direcaoX > -0.05) direcaoX = 0;
                if(direcaoY < 0.05 && direcaoY > -0.05) direcaoY = 0;

                float velocidade = 25;
                //Log.i("SENSOR", "X: "+direcaoX+" Y: "+direcaoY);
                Geometria forma = formas.get(0);

                float moveX = forma.getX() + (velocidade*direcaoX);
                float moveY = forma.getY() + (velocidade*direcaoY);

                if(moveX > width - forma.getWidth())
                    forma.move(width - forma.getWidth(), forma.getY());
                else if(moveX < 0)
                    forma.move(0, forma.getY());
                else
                    forma.move(moveX, forma.getY());
                if(moveY > height - forma.getHeight())
                    forma.move(forma.getX(), height - forma.getHeight());
                else if(moveY < 0 )
                    forma.move(forma.getX(), 0);
                else {
                    forma.move(forma.getX(), moveY);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
