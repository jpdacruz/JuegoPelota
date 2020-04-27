package com.jpdacruz.hilos_sonido.main.Model;

import android.view.View;
import android.widget.Button;

import com.jpdacruz.hilos_sonido.R;
import com.jpdacruz.hilos_sonido.main.InterfaceMain;

public class ModelMain implements InterfaceMain {

    private int modeDificultad;
    public ModelMain() {
    }

    public void setModeDificultad(int modeDificultad) {
        this.modeDificultad = modeDificultad;
    }

    @Override
    public int setDificultad(View view) {

        String dificultadString = (String) ((Button) view).getText();

        if (dificultadString.equals(R.string.medio)) setModeDificultad(2);
        if (dificultadString.equals(R.string.dificil)) setModeDificultad(3);

        return modeDificultad;
    }
}
