package com.example.caliste_proyecto.DietasAc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.R;

import java.util.List;

public class DietaAc  extends AppCompatActivity {
    private TextView tituloDieta, Descripcion;
    private ImageView fotodieta;
    private Button BtnVolver;
    private DietasExcerciseRepository repository;  // Cambiado a DietasRepository

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietas); // Asegúrate de que este sea el layout correcto para dietas
        // Inicio el repositorio para dietas
        repository = new DietasExcerciseRepository();  // Usamos el repositorio específico para dietas
        // Inicio las vistas
        initializeViews();
        // Cargar los datos de las dietas
        loadDietas();
        // Configurar los botones
        setupButtons();

    }

    private void initializeViews() {
        tituloDieta = findViewById(R.id.tituloDieta);
        Descripcion = findViewById(R.id.Descripcion);
        BtnVolver = findViewById(R.id.BtnVolver);
        fotodieta = findViewById(R.id.fotodieta); // Asegúrate de que este ID exista en tu layout

    }

    private void loadDietas() {
        // Cargar las dietas desde el repositorio
        repository.getAllDietas(new ApiCallback<List<Dietas>>() {
            @Override
            public void onSuccess(List<Dietas> dietas) {
                runOnUiThread(() -> {
                    // Encontramos el ListView y le asignamos el adaptador
                    ListView listViewDietas = findViewById(R.id.listViewDietas); // Asegúrate de que este ID exista en tu layout
                    DietasAdapter adapter = new DietasAdapter(DietaAc.this, dietas);  // Cambiado el adaptador
                    listViewDietas.setAdapter(adapter);
                });

            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(DietaAc.this, "Error al cargar las dietas: " + error, Toast.LENGTH_SHORT).show();
                });

            }

        });

    }
    private void setupButtons() {
        // Configurar el botón de volver
        BtnVolver.setOnClickListener(v -> finish());
    }
}
