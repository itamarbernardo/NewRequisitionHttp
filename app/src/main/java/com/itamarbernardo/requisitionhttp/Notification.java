package com.itamarbernardo.requisitionhttp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Dalton Willians on 20/12/2016.
 */
public class Notification extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprincipal);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(R.drawable.fire);
    }
}
