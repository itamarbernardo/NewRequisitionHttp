package com.itamarbernardo.requisitionhttp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANAFLAVIA on 13/12/2016.
 */
public class ProtocoloActions extends Thread{

//    private Queue<Integer> fila;
    private List<Integer> fila;
    private String caminho;
    private String mensagem;
    private SensorAnswer sa;
//    private AcessoHome ac;


    public ProtocoloActions(String caminho) {
        //this.fila = new LinkedList<Integer>();
        this.fila = new ArrayList<>();
        this.sa = new SensorAnswer();
        this.caminho = caminho;
 //       ac = new AcessoHome();
    }

    public synchronized void adicionarComandoEnvio(int valorEnvio) {
        this.fila.add(valorEnvio);
        //this.fila.offer(valorEnvio);
    }

    public synchronized List<Integer> getFila() {
        return fila;
    }


    public synchronized void setFila(List<Integer> fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        enviaPedidos();
    }

    public void enviaPedidos() {

        while (true) {

            if (this.fila.size() != 0) {
                for (Integer p : fila) {
                    try {

                        AcessoHome ac = new AcessoHome();
                        mensagem = ac.consumerGet(caminho + p);
                        sa = ac.transformaJson(mensagem);

                        Log.i("PEDIDOS", String.valueOf(sa.getValue()));
                        //Esses não precisa mostrar, por enquanto!
                        fila.remove(p);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                }else{
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }


    }

}
