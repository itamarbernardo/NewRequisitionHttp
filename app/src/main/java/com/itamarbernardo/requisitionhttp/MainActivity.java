package com.itamarbernardo.requisitionhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String mensagem;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AcessoRest ac = new AcessoRest();
        String urlMaps = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyDxSPaFPYw4VHoODgbDcoRWjj68zeUkEu8";
        String wsLocal = "192.168.0.104:8084/HomeService/";
        mensagem = ac.exemploGet(wsLocal);
        Log.i("JSON", mensagem);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void mostraMsg(View arg0){
        editText.setText(mensagem);
    }



}
