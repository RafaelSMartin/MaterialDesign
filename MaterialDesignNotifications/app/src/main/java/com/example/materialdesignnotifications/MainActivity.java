package com.example.materialdesignnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Context context;
    private Notification notification;
    private long millisToWait = 65;
    private long millistoVibrate = 150;
    private long millistoVibrate2 = 300;
    private long millisToWait2 = 100;
    private long millisToWait3 = 30;
    private int onMillis = 1000;
    private int offMillis = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        //Instancio la notificacion
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        //Construyo la vibracion
        long[] vibratePattern = new long[]{
                millisToWait, millistoVibrate,
                millisToWait, millistoVibrate,
                millisToWait, millistoVibrate2,
                millisToWait2, millistoVibrate,
                millisToWait3, millistoVibrate,
        };

        //Construyo el sonido de alarma
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Construyo la notificacion
        //El título, el texto y el icono pequeño son obligatorios para que la notificación pueda mostrarse.
        notification = builder
                .setContentTitle("Titulo")
                .setContentText("Esto es una notificacion")
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon((((BitmapDrawable)getResources().getDrawable(R.drawable.scanner_icon)).getBitmap())) //Bitmap
                .setColor(Color.parseColor("#8BC34A"))
                .setVibrate(vibratePattern)
                //.setLights(Color.RED, onMillis, offMillis)
                //.setSound(alarmSound)
                //.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentInfo("ContentInfo")
                .setTicker("Alerta!")
                .build();


        Button bNotif = (Button) findViewById(R.id.b_notificacion);
        bNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestro la notificacion
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(0x1234, notification);
            }
        });





    }




}
