package com.example.materialdesignsnackbarandtoast;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static com.example.materialdesignsnackbarandtoast.R.id.snackbar;

public class MainActivity extends AppCompatActivity {

    private String text;
    private int duration;
    private Snackbar snackBar;
    private int gravity, xOffset, yOffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bSnackBar = (Button) findViewById(snackbar);

        bSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                text = "SnackBar Material Design";
                duration = (int) TimeUnit.SECONDS.toMillis(10); //Sirve para el SnackBar y el toast

                snackBar = Snackbar.make(view, text, duration);

                // Obtén la vista del snackbar
                View snackbarView = snackBar.getView();
                //Cambia el color del SnackBar
                int snackBarTextId = android.support.design.R.id.snackbar_text;
                TextView textView = (TextView) snackbarView.findViewById(snackBarTextId);
                textView.setTextColor(Color.GREEN);
                // Cambia el fondo del snackbar
                snackbarView.setBackgroundColor(Color.MAGENTA);

                snackBar
                        .setActionTextColor(getResources().getColor(R.color.rojo))
                        .setAction("Action", new View.OnClickListener(){
                   @Override
                    public void onClick(View v){
                       //tu accion aqui
                       gravity = Gravity.CENTER;
                       xOffset = 0;
                       yOffset = 0;
                       Toast toast = Toast.makeText(MainActivity.this, "onClick Action", Toast.LENGTH_SHORT);
                       toast.setGravity(gravity, xOffset, yOffset);
                       toast.show();

                   }
                });

                snackBar.setCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar snackbar, int event){
                        //Tu accion dismiss aqui
                        Toast toast = Toast.makeText(MainActivity.this, "onDimissed", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.LEFT, 0, 0);
                        toast.show();
                    }
                    @Override
                    public void onShown(Snackbar snackbar){
                        //Tu accion cuando se muestre el snackbar aqui
                        Toast.makeText(MainActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                    }
                });
                snackBar.show();
            }
        });

        Button dismissSnackBar = (Button) findViewById(R.id.dismiss_snackbar);
        dismissSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(snackBar!=null){
                    snackBar.dismiss();
                }

            }
        });

        Button dismissToast   = (Button) findViewById(R.id.dismiss_toast);
        dismissToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast toast = Toast.makeText(MainActivity.this, "Dismiss Toast", Toast.LENGTH_SHORT);
                toast.show();
                toast.cancel();
            }
        });


        Button customToast = (Button) findViewById(R.id.custom_toast);
        customToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Crea la instancia
                Toast toast = Toast.makeText(MainActivity.this, "Custom toast",Toast.LENGTH_SHORT);

                //Configuro el color del mensaje
                TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
                textView.setTextColor(Color.YELLOW);

                //Configura el color de fondo
                toast.getView().setBackgroundColor(getResources().getColor(R.color.rojo));

                //Muestro el toast
                toast.show();
            }
        });


        Button customToastXml = (Button) findViewById(R.id.custom_toast_xml);
        customToastXml.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Crea la instancia
                Toast toast = new Toast(getApplicationContext());

                //Infla la vista personalizada
                View viewToast = getLayoutInflater().inflate(R.layout.custom_toast, null);

                //Configura la vista
                toast.setView(viewToast);

                //Configura la duracion
                toast.setDuration(Toast.LENGTH_LONG);

                //Configura la posicion
                int margin = getResources().getDimensionPixelSize(R.dimen.toast_vertical_margin);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_VERTICAL, 0, margin);

                //Muestra el toast
                toast.show();


            }
        });



    }



}
