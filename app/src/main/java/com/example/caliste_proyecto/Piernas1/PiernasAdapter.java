package com.example.caliste_proyecto.Piernas1;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.caliste_proyecto.Espalda1.EspaldaExercise;
import com.example.caliste_proyecto.R;

import java.util.List;

public class PiernasAdapter extends BaseAdapter {

    private Context context;
    private List<PiernasExercise> exercises;

    public PiernasAdapter(Context context, List<PiernasExercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_piernas, parent, false);
        }

        PiernasExercise exercise = exercises.get(position);

        // Enlazar los elementos del ejercicio con las vistas
        TextView titulo = convertView.findViewById(R.id.tituloEjercicio);
        titulo.setText(exercise.getNombre());

        TextView repeticiones = convertView.findViewById(R.id.numeroRepeticiones);
        repeticiones.setText("Repeticiones: " + exercise.getRepeticiones());

        ImageView imagen = convertView.findViewById(R.id.fotoejer);

        if (exercise.getImagenUrl() != null && !exercise.getImagenUrl().isEmpty()) {
            Glide.with(context)
                    .load(exercise.getImagenUrl())
                    .placeholder(android.R.drawable.ic_menu_gallery) // Imagen temporal mientras carga
                    .error(android.R.drawable.ic_dialog_alert) // Imagen si hay error
                    .fitCenter() // Ajusta la imagen manteniendo su proporción
                    .into(imagen);
        } else {
            imagen.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // Botón "Ver Más"
        Button btnVerMas = convertView.findViewById(R.id.verMas);
        btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear y mostrar el Dialog con la descripción
                showDescriptionDialog(exercise.getDescripcion());
            }
        });

        return convertView;
    }
    // Método para mostrar un Dialog con la descripción
    private void showDescriptionDialog(String descripcion) {
        // Crear el AlertDialog para mostrar la descripción
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Descripción del Ejercicio");
        builder.setMessage(descripcion);
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }
}

