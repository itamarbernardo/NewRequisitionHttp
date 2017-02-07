package com.itamarbernardo.requisitionhttp;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * Created by marcelosiedler on 09/03/15.
 */
public class AcessoHome {

    private int  TIMEOUT_MILLISEC = 3000;

    public AcessoHome(){

    }

    public String consumerGet(String url)
    {

        HttpClient httpclient = new DefaultHttpClient();

        HttpGet chamadaget = new HttpGet(url);
        String retorno = "";

        try {
            //Permissões
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //Pega a resposta com dois parâmetros de entrata(o tipo -GET e o tipo de resposta - String)
            String responseBody = httpclient.execute(chamadaget,
                    responseHandler);

            retorno = responseBody;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Throwable t) {
            Log.i("erro", t.toString());
        }

        return retorno;


    }

    public SensorAnswer transformaJson(String mensagem){
        SensorAnswer sa = new SensorAnswer();
        Gson g = new Gson();
        Type modelo = new TypeToken<SensorAnswer>() {
        }.getType();

        sa = g.fromJson(mensagem, modelo);

        return sa;
    }

}
