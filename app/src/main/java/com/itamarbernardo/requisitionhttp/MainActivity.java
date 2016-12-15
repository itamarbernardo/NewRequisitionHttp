package com.itamarbernardo.requisitionhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String mensagem;
    private EditText editText;
    private SensorAnswer sa;
    private AcessoRest ac;
    private String wsLocal = "http://192.168.0.106:8084/MyNewHome/webresources/sensor?sensorId=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ac = new AcessoRest();
        sa = new SensorAnswer();

        //ProtocoloActions p = new ProtocoloActions();
        //p.start();
        //List<Integer> pedidos = new ArrayList<Integer>();
        //pedidos.add(1);
        //pedidos.add(1);
        //pedidos.add(2);

        //ProtocoloDeEnvio pe = new ProtocoloDeEnvio(pedidos);
        //pe.start();
        //-----------------------------
        // Comando para adicionar pedidos
        // protocoloAction.adicionarComandoEnvio(pergunta);


    }

    public void mostraMsg(View arg0){

        mensagem = ac.exemploGet(wsLocal + 1);
        sa = transformaJson(mensagem, sa);
        Log.i("GSON", String.valueOf(sa.getValue()));

        Log.i("JSON", mensagem);

        editText = (EditText) findViewById(R.id.editText);
        editText.setText(String.valueOf(sa.getValue()));

    }

    public SensorAnswer transformaJson(String mensagem, SensorAnswer sa){
        Gson g = new Gson();
        Type modelo = new TypeToken<SensorAnswer>() {
        }.getType();

        sa = g.fromJson(mensagem, modelo);

        return sa;
    }

}
