package com.jpdacruz.hilos_sonido.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jpdacruz.hilos_sonido.ayuda.AyudaActivity;
import com.jpdacruz.hilos_sonido.main.Model.ModelMain;
import com.jpdacruz.hilos_sonido.partida.PartidaActivity;
import com.jpdacruz.hilos_sonido.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void toHelp(View view) {

        startActivity(new Intent(this, AyudaActivity.class));
    }

    public void dificultad(View view){

        //de la vista toma el string del elemento que se toco
        String dificultadString = (String) ((Button) view).getText();

        int dificultad = 1;
        if (dificultadString.equals(getString(R.string.medio))) dificultad = 2;
        if (dificultadString.equals(getString(R.string.dificil))) dificultad = 3;

        Intent intent = new Intent(this, PartidaActivity.class);
        intent.putExtra("DIFICULTAD", dificultad);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode !=1 || resultCode != RESULT_OK) return;

        TextView textView = findViewById(R.id.texto_record);
        textView.setText("Record: " + data.getIntExtra("JUEGUITOS",0));
    }
}
