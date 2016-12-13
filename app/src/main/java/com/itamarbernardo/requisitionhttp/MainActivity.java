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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProtocoloActions p = new ProtocoloActions();
        p.start();
        List<Integer> pedidos = new ArrayList<Integer>();
        pedidos.add(1);
        pedidos.add(1);
        pedidos.add(2);

        ProtocoloDeEnvio pe = new ProtocoloDeEnvio(pedidos);
        pe.start();
        //-----------------------------
        // Comando para adicionar pedidos
        // protocoloAction.adicionarComandoEnvio(pergunta);


    }

    public void mostraMsg(View arg0){
        AcessoRest ac = new AcessoRest();
 //       String urlMaps = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDxSPaFPYw4VHoODgbDcoRWjj68zeUkEu8";
 //       String wsLocal = "http://192.168.0.104:8084/MyNewHome/webresources/sensor?sensorId=1";
        String alunosWS = "http://192.168.0.104:8084/VideoAulaWebServices/webresources/aulaws/usuario/get";

        mensagem = ac.exemploGet(alunosWS);
        Gson g = new Gson();
        SensorAnswer sa = new SensorAnswer();
        Type modelo = new TypeToken<SensorAnswer>() {
        }.getType();

        sa = g.fromJson(mensagem, modelo);

        Log.i("JSON", mensagem);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(sa.getValue());

    }



}
