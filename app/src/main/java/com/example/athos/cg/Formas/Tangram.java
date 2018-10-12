package com.example.athos.cg.Formas;

import java.util.ArrayList;

public class Tangram {

    float[] desenho ;
    ArrayList<Geometria> formas = new ArrayList<>();
    
    float largura;
    float altura;
    float unidade;
    float x,y;

    public Tangram(float largura, float x, float y, float[] desenho){
        this.desenho = desenho;
        this.largura = largura;
        this.altura = largura;
        this.unidade = largura*0.1f;
        this.x = x;
        this.y = y;

    }
    
    private void desenhaFormas(){
        formas.add(new Triangulo(unidade*4, unidade*2, largura*desenho[0], largura*desenho[1]).rotateZ((int)desenho[2]));
        formas.add(new Triangulo(unidade*4, unidade*2, largura*desenho[3], largura*desenho[4]).rotateZ((int)desenho[5]));
        formas.add(new Triangulo(unidade*2, unidade, largura*desenho[6], largura*desenho[7]).rotateZ((int)desenho[8]));
        formas.add(new Triangulo(unidade*2, unidade, largura*desenho[9], largura*desenho[10]).rotateZ((int)desenho[11]));
        formas.add(new Triangulo((float)Math.sqrt(Math.pow(unidade*2, 2)+Math.pow(unidade*2, 2)),(float)Math.sqrt(Math.pow(unidade*2, 2)+Math.pow(unidade*2, 2))/2, largura*desenho[12], largura*desenho[13]).rotateZ(225).rotateZ((int)desenho[14]));
        formas.add(new Quadrado((float)Math.sqrt(Math.pow(unidade, 2)+Math.pow(unidade, 2)), largura*desenho[15], largura*desenho[16]).rotateZ(45).rotateZ((int)desenho[17]));
        formas.add(new Paralelepipedo(unidade*3, unidade, largura*desenho[18], largura*desenho[19]).rotateZ((int)desenho[20]).rotateY((desenho[21]==1)?true:false));
    }


}
