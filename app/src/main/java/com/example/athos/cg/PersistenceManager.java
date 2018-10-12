package com.example.athos.cg;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

public class PersistenceManager {
    public static void save(Activity activity, float[] formas, String texto){
        SharedPreferences shared = activity.getSharedPreferences("config", Activity.MODE_PRIVATE);
        int j = 0;
        for(;;j++)
        {
            if(!shared.contains("tangram"+j)) break;
        }

        String dados = "";
        for (int i = 0; i < formas.length; i++) {
            dados += (formas[i]+",");
            Log.i("INFO",i+" "+formas[i]);
        }
        //Log.i("INFO","SET: "+dados);

        SharedPreferences.Editor editor = shared.edit();
        editor.putString("tangram"+j, dados);
        editor.putString("tangramS"+j, texto);
        editor.commit();
    }

    public static float[] getValueFloat(Activity activity, int indice){
        SharedPreferences shared = activity.getSharedPreferences("config", Activity.MODE_PRIVATE);
        if(shared.contains("tangram"+indice)){
            String dados = shared.getString("tangram"+indice, "");
            String aux = "";
            float[] a = new float[22];
            int j = 0;
            for(int i = 0; i < dados.length(); i++){
                if(dados.charAt(i) == ','){
                    a[j] = Float.parseFloat(aux);
                    aux = "";
                    j++;
                }
                else {
                    aux = aux+dados.charAt(i);
                }
            }
            Log.i("INFO","GET: "+dados);
            return a;
        }
        return null;
    }
    public static String getValueString(Activity activity, int indice){
        SharedPreferences shared = activity.getSharedPreferences("config", Activity.MODE_PRIVATE);
        if(shared.contains("tangramS"+indice)){
            return shared.getString("tangramS"+indice, "");
        }
        return null;
    }
    public static void deleteAll(Activity activity){
        activity.getSharedPreferences("config", 0).edit().clear().commit();
    }
}
