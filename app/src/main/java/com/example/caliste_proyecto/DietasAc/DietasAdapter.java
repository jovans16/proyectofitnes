package com.example.caliste_proyecto.DietasAc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.caliste_proyecto.R;

import java.util.List;

public class DietasAdapter extends BaseAdapter {

    private Context context;
    private List<Dietas> dietas; // Cambiado a List<Dietas>

    public DietasAdapter(Context context, List<Dietas> dietas) { // Cambiado el nombre del parámetro
        this.context = context;
        this.dietas = dietas; // Cambiado a dietas
    }

    @Override
    public int getCount() {
        return dietas.size(); // Cambiado a dietas
    }

    @Override
    public Object getItem(int position) {
        return dietas.get(position); // Cambiado a dietas
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dietas, parent, false);
        }

        Dietas dieta = dietas.get(position);
        // Enlazar los elementos de la dieta con las vistas
        TextView nombre = convertView.findViewById(R.id.tituloDieta);

        nombre.setText(dieta.getNombre());
        TextView descripcion = convertView.findViewById(R.id.Descripcion);
        descripcion.setText(dieta.getDescripcion());

        ImageView imagen = convertView.findViewById(R.id.fotodieta);

        if (dieta.getImagenUrl() != null && !dieta.getImagenUrl().isEmpty()) {
            Glide.with(context)
                    .load(dieta.getImagenUrl()) // Cambiado a getImagenUrl()
                    .placeholder(android.R.drawable.ic_menu_gallery) // Imagen temporal mientras carga
                    .error(android.R.drawable.ic_dialog_alert) // Imagen si hay error
                    .fitCenter() // Ajusta la imagen manteniendo su proporción
                    .into(imagen);
        } else {
            imagen.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        return convertView;

    }

}
