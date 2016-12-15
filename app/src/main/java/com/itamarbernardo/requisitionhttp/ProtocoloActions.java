package com.itamarbernardo.requisitionhttp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ANAFLAVIA on 13/12/2016.
 */
public class ProtocoloActions extends Thread{

    private Queue<Integer> fila;

    public ProtocoloActions() {
        this.fila = new LinkedList<Integer>();
    }

    public synchronized void adicionarComandoEnvio(int valorEnvio) {
        this.fila.offer(valorEnvio);
    }

    public synchronized Queue<Integer> getFila() {
        return fila;
    }

    public synchronized void setFila(Queue<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        enviaPedidos();
    }

    public void enviaPedidos() {

        while (true) {

            if (this.fila.size() != 0) {

                try {

                    AcessoRest ac = new AcessoRest();
                    //String mensagem = ac.exemploGet("http://192.168.0.104:8084/TestHome/webresources//sensor?sensorId==" + this.fila.poll());

                    Gson g = new Gson();
                    SensorAnswer s = new SensorAnswer();
                    //Type modelo = new TypeToken<SensorAnswer>() {
                    //}.getType();

                    //s = g.fromJson(mensagem, modelo);
                    //Esses não precisa mostrar, por enquanto!

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

}
