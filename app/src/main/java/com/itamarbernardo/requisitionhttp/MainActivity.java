package com.itamarbernardo.requisitionhttp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private String mensagem;
    private EditText editText;
    private SensorAnswer sa;
    private AcessoHome ac;
    private String wsLocal = "http://192.168.0.106:8084/MyNewHome/webresources/sensor?sensorId=";
    private ProtocoloDeEnvio pe;
    private ProtocoloActions p;

    //Aqui eu chamo a activity tela principal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprincipal);
        ac = new AcessoHome();
        sa = new SensorAnswer();

        p = new ProtocoloActions(wsLocal);
        p.start();
        List<Integer> pedidos = new ArrayList<Integer>();
        pedidos.add(1);

        pe = new ProtocoloDeEnvio(pedidos, wsLocal);
        pe.start();
        //-----------------------------
        //Verifica se há fogo e lança a notificação
        if(pe.enviaPedidos() == true){

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //Builder que dá as caracteristicas da notificação
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setTicker("ATENÇÃO!");
            builder.setContentTitle("Casa pegando fogo!");
            builder.setContentText("Sensor detectou atividade suspeita!");
            builder.setSmallIcon(R.drawable.iconfirenot);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fire));

            Notification notification = builder.build();
            //Faz o celular vibrar
            notification.vibrate = new long[]{150, 300, 150, 600};
            notificationManager.notify(R.drawable.fire, notification);

            //Toque do celular
            try {
                Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone toque = RingtoneManager.getRingtone(this, som);
                toque.play();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }//else{

        //}
    }

    //Notification vem aqui!
    public void gerarNotificacao(View view) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Builder que dá as caracteristicas da notificação
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("ATENÇÃO!");
        builder.setContentTitle("Casa pegando fogo!");
        builder.setContentText("Sensor detectou atividade suspeita!");
        builder.setSmallIcon(R.drawable.iconfirenot);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fire));

        Notification notification = builder.build();
        //Faz o celular vibrar
        notification.vibrate = new long[]{150, 300, 150, 600};
        notificationManager.notify(R.drawable.fire, notification);

        //Toque do celular
        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TV ligada
    public void actionButtonTVON(View arg0){

        p.adicionarComandoEnvio(4);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ligar TV");
        alertDialog.setMessage("TV ligada!");
        alertDialog.show();
    }
    //TV desligada
    public void actionButtonTVOFF(View arg0){

        p.adicionarComandoEnvio(4);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Desligar TV");
        alertDialog.setMessage("TV desligada!");
        alertDialog.show();
    }
    //Aumentar volume da TV
    public void actionButtonTVVOLUP(View arg0){

        p.adicionarComandoEnvio(5);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Aumentar volume");
        alertDialog.setMessage("Volume aumentado!");
        alertDialog.show();
    }
    //Diminuir volume da TV
    public void actionButtonTVVOLDN(View arg0){

        p.adicionarComandoEnvio(6);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Diminuir volume");
        alertDialog.setMessage("Volume diminuido!");
        alertDialog.show();
    }
    //Próximo canal
    public void actionButtonTVPCHN(View arg0){

        p.adicionarComandoEnvio(7);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Próximo canal");
        alertDialog.setMessage("Próximo canal!");
        alertDialog.show();
    }
    //Voltar Canal
    public void actionButtonTVVCHN(View arg0){

        p.adicionarComandoEnvio(6);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Voltar canal");
        alertDialog.setMessage("Voltar canal!");
        alertDialog.show();
    }
    //Ligar ar
    public void actionButtonAirON(View arg0){
        p.adicionarComandoEnvio(3);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ligar Ar");
        alertDialog.setMessage("Ar ligado!");
        alertDialog.show();
    }
    //Desligar ar
    public void actionButtonAirOFF(View arg0){
        p.adicionarComandoEnvio(3);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Desligar Ar");
        alertDialog.setMessage("Ar desligado!");
        alertDialog.show();
    }
    //Ligar lâmpada
    public void actionButtonLampadaON(View arg0){
        p.adicionarComandoEnvio(2);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ligar Lâmpada");
        alertDialog.setMessage("Lâmpada ligada!");
        alertDialog.show();
    }
    //Desligar lâmpada
    public void actionButtonLampadaOFF(View arg0){
        p.adicionarComandoEnvio(2);
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ligar Lâmpada");
        alertDialog.setMessage("Lâmpada desligada!");
        alertDialog.show();
    }
    //Função chegar em casa
    public void actionHome(View arg0){
        p.adicionarComandoEnvio(9);

    }

    //Alternar de activities
    //Botão luz
    public void activityLuz(View view){ setContentView(R.layout.activity_luz);}
    //Alternar de activities
    //Botão TV
    public void activityTV(View view){
        setContentView(R.layout.activity_tv);
    }

    //Alternar de activities
    //Botão Ar
    public void activityAir(View view){
        setContentView(R.layout.activity_ar);
    }

    //Botão voltar
    public void back(View view){
        setContentView(R.layout.telaprincipal);
    }
}


