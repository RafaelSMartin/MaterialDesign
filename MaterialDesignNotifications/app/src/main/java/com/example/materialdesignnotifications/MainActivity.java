package com.example.materialdesignnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

/**
 * Realizado por Rafael S. Martin Gonzalez
 */


/**
 *  NOTIFICACIONES BASICAS BUENA PRACTICA
 *  I. Notifica al usuario únicamente con información importante direccionada específicamente a él.
 *  II. Para notificaciones mandadas por cualquier persona, incluye la imagen de la persona.
 *  III. Cuando el usuario toca una notificación, permite al usuario inmediatamente realizar una acción.
 *      Puede ser abrir una vista de detalle, como un mensaje o una vista resumen para múltiple notificaciones.
 *  IV. El recortado circular se utiliza por defecto cuando no se utiliza el método setLargeIcon y se utiliza
 *      el método setSmallIcon. En caso de utilizar el método setLargeIcon la imagen circular ha de realizarse manualmente.
 *
 *  Nota: Las notificaciones tienen otro aspectos diferentes en Holo y Material, además,
 *      algunas funcionalidades como el tintado no están disponibles.
 *
 *  NOTIFICACIONES BIGTEXTSTYLE
 *  NOTA: BigTextStyle se usa para mostrar grandes cantidades de texto.
 *  Aproximadamente su cuerpo puede albergar unos 450 caracteres.
 *  El resto del texto se cortará sin usar una elipsis.
 *
 *  NOTIFICACIONES BIGPICTURESTYLE
 *  NOTA: BigPictureStyle se utiliza para mostrar contenido rico en imágenes.
 *  Los límites de la imagen es la longitud del teléfono en anchura por 256dp de altura.
 *  El resto de la imagen se recortará con un cropCenter.
 *
 *  NOTIFICACIONES BIGINBOXSTYLE
 *  NOTA: El Inbox Style permite que una notificación se componga de varias líneas con texto corto.
 *  Este estilo acepta hasta 7 líneas. Por encima de esas líneas, la notificación provocará
 *  una elipsis con una linea adicional con "..."
 *
 *  NOTIFICACIONES BIGSTYLE BUENA PRACTICA - solo a partir de api16 se veran
 *  I. Notifica al usuario únicamente con información importante direccionada específicamente a él.
 *  II. Para notificaciones mandadas por cualquier persona, incluye la imagen de la persona.
 *  III. Cuando el usuario toca una notificación, permite al usuario inmediatamente realizar una acción.
 *      Puede aser abrir una vista de detalle, como un mensaje, ona vista resumen para múltiple notificaciones.
 *  IV. El recortado circular se utiliza por defecto cuando no se utiliza el método setLargeIcon
 *      y se utiliza el método setSmallIcon. En caso de utilizar el método setLargeIcon
 *      la imagen circular ha de realizarse manualmente.
 *
**/

public class MainActivity extends AppCompatActivity {

    Context context;

    private static final int NOTIF_ALERTA_ID_COMPAT = 1;
    private static final int NOTIF_ALERTA_ID_SERVICE = 2;
    private static final int NOTIF_ALERTA_ID_BIG_STYLE = 3;
    private static final int NOTIF_ALERTA_ID_BIG_STYLE_PICTURE = 4;
    private static final int NOTIF_ALERTA_ID_BIG_STYLE_INBOX = 5;


    private Notification notification;
    private Notification notificationBigStyleText, notificationBigStylePicture, notificationBigStyleInbox;

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
        //Instancio la notificacion BigStyle
        NotificationCompat.Builder builderBigText = new NotificationCompat.Builder(context);
        NotificationCompat.Builder builderBigPicture = new NotificationCompat.Builder(context);
        NotificationCompat.Builder builderBigInbox = new NotificationCompat.Builder(context);

        //Construyo un estilo a la notificacion BigStyleText, hay tres disponibles
        NotificationCompat.BigTextStyle styleBigText = new NotificationCompat.BigTextStyle(builderBigText);
        styleBigText
                .bigText("Las guías insisten en que cada app debería producir una única notificación para " +
                "no copar la lista de notificaciones. Utiliza InboxStyle para fusionar varias notificaciones " +
                "en una sola y evitar eso sin renunciar al histórico.")
                .setBigContentTitle("Titulo expandido")
                .setSummaryText("Texto resumen");
        //Construyo un estilo a la notificacion BigStylePicture
        NotificationCompat.BigPictureStyle styleBigPicture = new NotificationCompat.BigPictureStyle(builderBigPicture);
        styleBigPicture
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.scanner_icon))
                .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications))
                .setBigContentTitle("Titulo expandido")
                .setSummaryText("Texto resumen");
        //Construyo un estilo a la notificacion BigStyleInbox
        NotificationCompat.InboxStyle styleBigInbox = new NotificationCompat.InboxStyle(builderBigInbox);
        styleBigInbox
                .addLine("Esta es la linea: " + 1)
                .addLine("Esta es la linea: " + 2)
                .addLine("Esta es la linea: " + 3)
                .addLine("Esta es la linea: " + 4)
                .addLine("Esta es la linea: " + 5)
                .setBigContentTitle("Titulo Expandido")
                .setSummaryText("Texto resumen");

        //Instancio un Intent para asociarlo al PendingItent
        Intent notIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);

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
        //Incluyo en la construccion el PendingIntent
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
                .setContentIntent(contIntent)
                .build();


        //Contruyo la notificacion BigStyleText
        notificationBigStyleText = builderBigText
                .setContentTitle("TituloBigStyleText")
                .setContentText("Esto es una notificacion BigStyleText")
                .setSmallIcon(R.drawable.ic_notifications)
                .build();
        //Contruyo la notificacion BigStylePincture
        notificationBigStylePicture = builderBigPicture
                .setContentTitle("TituloBigStylePicture")
                .setContentText("Esto es una notificacion BigStylePicture")
                .setSmallIcon(R.drawable.ic_notifications)
                .build();

        //Construyo la notificacion BigStyleInbox
        notificationBigStyleInbox = builderBigInbox
                .setContentTitle("TituloBigStyleinbox")
                .setContentText("Esto es una notificacion BigStyleInbox")
                .setSmallIcon(R.drawable.ic_notifications)
                .build();

        Button bNotif = (Button) findViewById(R.id.b_notificacion);
        bNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestro la notificacion en materialdoc.es
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(NOTIF_ALERTA_ID_COMPAT, notification);

            }
        });

        Button bNotifService = (Button) findViewById(R.id.b_notificacion_service);
        bNotifService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Otra forma de mostrar la notificacion en sgOliver
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIF_ALERTA_ID_SERVICE, notification);
            }
        });

        Button bNotifCompatBigStyleText = (Button) findViewById(R.id.b_notificacion_bigstyle);
        bNotifCompatBigStyleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestro la notificacion
                NotificationManagerCompat notificationManagerBigStyleText = NotificationManagerCompat.from(context);
                notificationManagerBigStyleText.notify(NOTIF_ALERTA_ID_BIG_STYLE, notificationBigStyleText);
            }
        });

        Button bNotifCompatBigStylePicture = (Button) findViewById(R.id.b_notificacion_bigstyle_picture);
        bNotifCompatBigStylePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestro la notificacion
                NotificationManagerCompat notificationManagerBigStylePicture = NotificationManagerCompat.from(context);
                notificationManagerBigStylePicture.notify(NOTIF_ALERTA_ID_BIG_STYLE_PICTURE, notificationBigStylePicture);
            }
        });

        Button bNotifCompatBigStyleInbox = (Button) findViewById(R.id.b_notificacion_bigstyle_inbox);
        bNotifCompatBigStyleInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Muestro la notificacion
                NotificationManagerCompat notificationManagerBigStyleInbox = NotificationManagerCompat.from(context);
                notificationManagerBigStyleInbox.notify(NOTIF_ALERTA_ID_BIG_STYLE_INBOX, notificationBigStyleInbox);
            }
        });





    }




}
