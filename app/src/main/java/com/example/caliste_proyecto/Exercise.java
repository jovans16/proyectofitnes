package com.example.caliste_proyecto;

import android.os.Parcelable;
import android.os.Parcel;

import androidx.annotation.NonNull;

public class Exercise implements Parcelable {

    private int id;
    private String nombre;
    private String repeticiones;
    private String descripcion;
    private String imagen_url;

    // Constructor
    public Exercise(int id, String nombre, String repeticiones, String descripcion, String imagen_url) {
        this.id = id;
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.descripcion = descripcion;
        this.imagen_url = imagen_url;
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        repeticiones = in.readString();
        descripcion = in.readString();
        imagen_url = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return nombre; }
    public void setName(String nombre) { this.nombre = nombre; }

    public String getRepetitions() { return repeticiones; }
    public void setRepeticiones(String repetitions) { this.repeticiones = repetitions; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String description) { this.descripcion = description; }

    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String image_url) { this.imagen_url = image_url; }

    public long getDuracionSegundos(){
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(repeticiones);
        parcel.writeString(descripcion);
        parcel.writeString(imagen_url);
    }
}


