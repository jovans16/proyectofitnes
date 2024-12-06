package com.example.caliste_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrazoAc extends AppCompatActivity {

    private TextView tituloEntrenamiento, nivelEjercicio, dia, tituloEjercicio, numeroRepeticiones;
    private ImageView fotoejer, imagenEjercicio;
    private Button BtnVolver, verMas, btnIniciar;
    private ExerciseRepository repository;
    private List<Exercise> listViewEjercicios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brazo);

        // inicio el repositorio
        repository = new ExerciseRepository();

        // inicio mi interfaz
        initializeViews();

        // cargo los datos del ejercicio
        loadExerciseData();

        // configuro mis botones
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

    private void loadExerciseData() {
        repository.getAllExercises(new ApiCallback<List<Exercise>>() {
            @Override
            public void onSuccess(List<Exercise> exercises) {
                runOnUiThread(() -> {
                    ListView listViewEjercicios = findViewById(R.id.listViewEjercicios);
                    ejercicioAdapter adapter = new ejercicioAdapter(BrazoAc.this, exercises);
                    listViewEjercicios.setAdapter(adapter);
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(BrazoAc.this, "Error al cargar los ejercicios: " + error, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void setupButtons() {
        BtnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExerciseRepository repository = new ExerciseRepository();
                repository.getAllExercises(new ApiCallback<List<Exercise>>() {
                    @Override
                    public void onSuccess (List<Exercise> exercises) {
                        if (exercises == null || exercises.isEmpty()) {
                            Log.e("BrazoAc", "Lista de ejercicios vacía o nula.");
                            Toast.makeText(BrazoAc.this, "No se obtuvieron ejercicios.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startExercise(exercises);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(BrazoAc.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void startExercise(List<Exercise> exercises) {
        Toast.makeText(this, "Iniciando ejercicios...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, DetalleAct.class);
        intent.putParcelableArrayListExtra("listaEjercicios", new ArrayList<>(exercises));
        Log.d("BrazoAc", "Lista de ejercicios: " + exercises);

        startActivity(intent);
    }
}



