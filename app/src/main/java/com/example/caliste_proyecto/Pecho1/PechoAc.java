package com.example.caliste_proyecto.Pecho1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.R;

import java.util.List;

public class PechoAc extends AppCompatActivity {

    private TextView tituloEntrenamiento, nivelEjercicio, dia, tituloEjercicio, numeroRepeticiones;
    private ImageView fotoejer, imagenEjercicio;
    private Button BtnVolver, verMas, btnIniciar;
    private PechoExerciseRepository repository;  // Cambiado a AbdomenExerciseRepository

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pecho); // Cambiado a la actividad correspondiente

        // Iniciar el repositorio para ejercicios de abdomen
        repository = new PechoExerciseRepository();  // Usamos el repositorio específico para abdomen

        // Inicializar las vistas
        initializeViews();

        // Cargar los datos del ejercicio
        loadPechoExercises();

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

    private void loadPechoExercises() {
        // Cargar los ejercicios de abdomen desde el repositorio
        repository.getAllPechoExercises(new ApiCallback<List<PechoExercise>>() {
            @Override
            public void onSuccess(List<PechoExercise> exercises) {
                runOnUiThread(() -> {
                    // Encontramos el ListView y le asignamos el adaptador
                    ListView listViewEjercicios = findViewById(R.id.listViewEjerciciosPe);
                    PechoAdapter adapter = new PechoAdapter(PechoAc.this, exercises);  // Cambiado el adaptador
                    listViewEjercicios.setAdapter(adapter);
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(PechoAc.this, "Error al cargar los ejercicios: " + error, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Iniciando ejercicio de pecho...", Toast.LENGTH_SHORT).show();
    }
}