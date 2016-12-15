package com.itamarbernardo.requisitionhttp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANAFLAVIA on 13/12/2016.
 */
public class ProtocoloDeEnvio extends Thread{

    private List<Integer> pedidos;

    public ProtocoloDeEnvio(List<Integer> pedidos) {
        this.pedidos = new ArrayList<>();
        this.pedidos = pedidos;

    }

    @Override
    public void run() {
        enviaPedidos();
    }

    public void enviaPedidos() {

        while (true) {

            for (Integer p : pedidos) {
                try {
                    AcessoRest ac = new AcessoRest();
                    //String mensagem = ac.exemploGet("http://192.168.0.104:8084/TestHome/webresources//sensor?sensorId==" + p);

                    Gson g = new Gson();
                    SensorAnswer sa = new SensorAnswer();
                    //Type modelo = new TypeToken<SensorAnswer>() {
                    //}.getType();

                    //sa = g.fromJson(mensagem, modelo);

                    //if (sa.getValue() == 666) {
                        //Lança notificação para o usuário que a casa está pegando fogo
                    //    Log.i("NOTIFICACAO", "Tá pegando fogo");
                    //} else if (sa.getValue() == 999) {
                        //Lança notificação para o usuário que está faltando comida
                    //    Log.i("NOTIFICACAO", "O rango tá pouco");
                    //}
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

}
