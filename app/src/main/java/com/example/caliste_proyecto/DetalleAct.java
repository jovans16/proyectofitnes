package com.example.caliste_proyecto;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetalleAct extends AppCompatActivity {
    private List<Exercise> listViewEjercicios;
    private int ejercicioActual = 0;
    private CountDownTimer countDownTimer;
    private TextView Nombre_Ejercicio, Tiempo_Restante;


    private void initializeViews() {
        Nombre_Ejercicio = findViewById(R.id.Nombre_Ejercicio);
        Tiempo_Restante = findViewById(R.id.Tiempo_Restante);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerciciosini);

        initializeViews();

        // Obtengo lista de ejercicios del Intent
        listViewEjercicios = getIntent().getParcelableArrayListExtra("listaEjercicios");
        Log.d("DetalleAct", "Lista de ejercicios recibida: " + listViewEjercicios);

        if (listViewEjercicios != null && !listViewEjercicios.isEmpty()) {
            iniciarEjercicio();
        } else {
            Toast.makeText(this, "No se recibieron ejercicios", Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarEjercicio() {
        if (ejercicioActual < listViewEjercicios.size()) {
            Exercise ejercicio = listViewEjercicios.get(ejercicioActual);
            Nombre_Ejercicio.setText("Ejercicio: " + ejercicio.getName());

            countDownTimer = new CountDownTimer(ejercicio.getDuracionSegundos() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Tiempo_Restante.setText("Tiempo restante: " + millisUntilFinished / 1000 + " segundos");
                }

                @Override
                public void onFinish() {
                    Toast.makeText(DetalleAct.this, "Ejercicio completado: " + ejercicio.getName(), Toast.LENGTH_SHORT).show();
                    ejercicioActual++;
                    iniciarEjercicio();
                }
            }.start();
        } else {
            Toast.makeText(this, "¡Entrenamiento completado!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

