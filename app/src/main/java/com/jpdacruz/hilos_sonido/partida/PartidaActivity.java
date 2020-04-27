package com.jpdacruz.hilos_sonido.partida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class PartidaActivity extends AppCompatActivity {

    private int jueguito = 0;
    private Partida partida;
    private int dificultad;
    //cuadros por segundo;
    private int fps = 30;
    private Handler temporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        dificultad = bundle.getInt("DIFICULTAD");
        partida = new Partida(getApplicationContext(),dificultad);
        //en la partida se crea el layout programaticamente, entonces el contenView se setea con la clase partida

        setContentView(partida);

        //arranco handler
        temporizador = new Handler();
        temporizador.postDelayed(bolaMoviendo,2000);
    }

    private Runnable bolaMoviendo = new Runnable() {
        @Override
        public void run() {

            if (partida.movimientoBola()) finPartida();

            else {

                //
                partida.invalidate(); //elimina el contido del imageview y vuelve a crear el metodo partida onDraw

                //pone una pausa en el hilo despues de invalidar
                temporizador.postDelayed(bolaMoviendo,1000/fps);
            }
        }
    };

    /**
     * metodo onTouchEvent que recoge un evento de touch.
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event){

        int x = (int) event.getX();
        int y = (int) event.getY();
        if (partida.toque(x,y)) jueguito++;
        return false;
    }
    private void finPartida() {

        /**
         * borra el runneable
         */
        temporizador.removeCallbacks(bolaMoviendo);

        Intent intent = new Intent();
        intent.putExtra("JUEGUITOS", jueguito);

        setResult(RESULT_OK,intent);//envia como resultado ok al intent que lo origino, y lleva el valor jueguito
        //9*detiene el activity
        finish();
    }
}
