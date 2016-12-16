package com.itamarbernardo.requisitionhttp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANAFLAVIA on 13/12/2016.
 */
public class ProtocoloDeEnvio extends Thread{

    private List<Integer> pedidos;
    private String caminho;
    private String mensagem;
    private SensorAnswer sa;

    public ProtocoloDeEnvio(List<Integer> pedidos, String caminho) {
        this.pedidos = new ArrayList<>();
        this.pedidos = pedidos;
        this.sa = new SensorAnswer();
        this.caminho = caminho;
    }

    @Override
    public void run() {
        enviaPedidos();
    }

    public void enviaPedidos() {

        while (true) {

            for (Integer p : pedidos) {
                try {
                    AcessoHome ac = new AcessoHome();
                    mensagem = ac.consumerGet(caminho + p);
                    sa = ac.transformaJson(mensagem);

                    Log.i("Valor", String.valueOf(p));
                    Log.i("Valor", String.valueOf(sa.getValue()));
                    if (sa.getValue() == 222) {
                        //Lança notificação para o usuário que a casa está pegando fogo
                        Log.i("NOTIFICACAO", "Tá pegando fogo");
                    }
                    else{
                        Log.i("NOTIFICACAO", "Código não reconhecido");
                    }

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
