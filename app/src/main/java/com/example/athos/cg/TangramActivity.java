package com.example.athos.cg;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


//Esta classe implementa os metodos necessarios para
//utilizar a biblioteca OpenGL no desenho grafico que
//será apresentado na tela pela superficie de desenho


public class TangramActivity extends AppCompatActivity {

    GLSurfaceView superficieDesenho = null;
    Renderizador render = null;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Valida a var de referencia para a superficie
        superficieDesenho = new GLSurfaceView(this);

        //valida a variavel de refenrencia para o renderizador
        render  = new Renderizador(this);

        //Associa o renderizador a superficie de desenho
        superficieDesenho.setRenderer(render);

//        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//
//        sensorManager.registerListener(render, sensor, SensorManager.SENSOR_DELAY_GAME);
//        sensorManager.unregisterListener(render);

        superficieDesenho.setOnTouchListener(render);


        //Publica a superficie de desenho na tela do App
        setContentView(superficieDesenho);
    }
}
