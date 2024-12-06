package com.example.caliste_proyecto.Piernas1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.Piernas1.PiernasExerciseRepository;
import com.example.caliste_proyecto.R;

import java.util.List;

public class PiernasAc  extends AppCompatActivity {
    private TextView tituloEntrenamiento, nivelEjercicio, dia, tituloEjercicio, numeroRepeticiones;
    private ImageView fotoejer, imagenEjercicio;
    private Button BtnVolver, verMas, btnIniciar;
    private PiernasExerciseRepository repository;  // Cambiado a AbdomenExerciseRepository

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piernas); // Cambiado a la actividad correspondiente

        // Iniciar el repositorio para ejercicios de abdomen
        repository = new PiernasExerciseRepository();  // Usamos el repositorio específico para abdomen

        // Inicializar las vistas
        initializeViews();

        // Cargar los datos del ejercicio
        loadPiernasExercises();

        // Configurar los botones
        setupButtons();
    }

    private void initializeViews() {
        tituloEntrenamiento = findViewById(R.id.tituloEntrenamiento);
        nivelEjercicio = findViewById(R.id.nivelEjercicio);
        dia = findViewById(R.id.dia);
        fotoejer = findViewById(R.id.fotoejer);
        tituloEjercicio = findViewById(R.id.tituloEjercicio);
        numeroRepeticiones = findViewById(R.id.numeroRepeticiones);
        BtnVolver = findViewById(R.id.BtnVolver);
        verMas = findViewById(R.id.verMas);
        btnIniciar = findViewById(R.id.btnIniciar);
    }

    private void loadPiernasExercises() {
        // Cargar los ejercicios de abdomen desde el repositorio
        repository.getAllPiernasExercises(new ApiCallback<List<PiernasExercise>>() {
            @Override
            public void onSuccess(List<PiernasExercise> exercises) {
                runOnUiThread(() -> {
                    // Encontramos el ListView y le asignamos el adaptador
                    ListView listViewEjercicios = findViewById(R.id.listViewEjerciciosPi);
                    PiernasAdapter adapter = new PiernasAdapter(PiernasAc.this, exercises);  // Cambiado el adaptador
                    listViewEjercicios.setAdapter(adapter);
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(PiernasAc.this, "Error al cargar los ejercicios: " + error, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void setupButtons() {
        // Configurar el botón de volver
        BtnVolver.setOnClickListener(v -> finish());

        // Configurar el botón para iniciar el ejercicio
        btnIniciar.setOnClickListener(view -> {
            // Lógica para iniciar el ejercicio
            startExercise();
        });
    }

    private void startExercise() {
        // Puedes agregar la lógica para iniciar el ejercicio aquí
        Toast.makeText(this, "Iniciando ejercicio de piernas...", Toast.LENGTH_SHORT).show();
    }
}