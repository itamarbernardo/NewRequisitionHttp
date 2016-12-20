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
    private boolean verificaFogo = false;

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

    public boolean enviaPedidos() {

        while (true) {

            for (Integer p : pedidos) {
                try {
                    AcessoHome ac = new AcessoHome();
                    mensagem = ac.consumerGet(caminho + p);
                    sa = ac.transformaJson(mensagem);

                    Log.i("Valor", String.valueOf(p));
                    Log.i("Valor", String.valueOf(sa.getValue()));
                    if (sa.getValue() == 777) {
                        Log.i("NOTIFICACAO", "Tá pegando fogo");
                        verificaFogo = true;

                    }
                    else if(sa.getValue() == 333){
                        Log.i("NOTIFICACAO", "Tudo tranquilo");
                        verificaFogo = false;
                    }
                    else{
                        Log.i("NOTIFICACAO", "Código não reconhecido");
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            return verificaFogo;
        }

    }

}
