package com.example.caliste_proyecto.TodoCP;

public class TodoExercise {
    private int id;
    private String nombre;
    private String repeticiones;
    private String descripcion;
    private String imagen_url;

    // Constructor
    public TodoExercise(int id, String nombre, String repeticiones, String descripcion, String imagen_url) {
        this.id = id;
        this.nombre = nombre;
        this.repeticiones = repeticiones;
        this.descripcion = descripcion;
        this.imagen_url = imagen_url;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRepeticiones() { return repeticiones; }
    public void setRepeticiones(String repeticiones) { this.repeticiones = repeticiones; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagenUrl() { return imagen_url; }
    public void setImagenUrl(String imagen_url) { this.imagen_url = imagen_url; }
}

