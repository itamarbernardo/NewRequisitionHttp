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
    private AcessoHome ac;
    private String wsLocal = "http://192.168.0.101:8084/MyNewHome/webresources/sensor?sensorId=";
    private ProtocoloDeEnvio pe;
    private ProtocoloActions p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ac = new AcessoHome();
        sa = new SensorAnswer();

        p = new ProtocoloActions(wsLocal);
        p.start();
        List<Integer> pedidos = new ArrayList<Integer>();
        pedidos.add(2);

        pe = new ProtocoloDeEnvio(pedidos, wsLocal);
        pe.start();
        //-----------------------------

    }

    public void ligarTV(View arg0){

        p.adicionarComandoEnvio(1);

    }

    public void ligarAr(View arg0){
        p.adicionarComandoEnvio(3);

    }

    public void ligarLuz(View arg0){
        p.adicionarComandoEnvio(4);

    }

}
