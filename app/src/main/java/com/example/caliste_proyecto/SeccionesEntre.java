package com.example.caliste_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.caliste_proyecto.Abdomen1.AbdomenAc;
import com.example.caliste_proyecto.DietasAc.DietaAc;
import com.example.caliste_proyecto.Espalda1.EspaldaAc;
import com.example.caliste_proyecto.Pecho1.PechoAc;
import com.example.caliste_proyecto.Piernas1.PiernasAc;
import com.example.caliste_proyecto.TodoCP.TodoAc;

public class SeccionesEntre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secciones);

        findViewById(R.id.brazoLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Brazo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, BrazoAc.class);
                startActivity(intent); // Inicia la nueva actividad
            }

        });

        findViewById(R.id.abdominalesLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Abdominales", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, AbdomenAc.class);
                startActivity(intent); // Inicia la nueva actividad
            }
        });

        findViewById(R.id.pechoLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Pecho", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, PechoAc.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.espaldaLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Espalda", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, EspaldaAc.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.piernasLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Piernas", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, PiernasAc.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.todoCuerpoLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Ejercicio de Todo el cuerpo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, TodoAc.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.dietaLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeccionesEntre.this, "Dietas", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeccionesEntre.this, DietaAc.class);
                startActivity(intent);
            }
        });
    }
}

