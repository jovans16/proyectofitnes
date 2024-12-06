package com.example.caliste_proyecto;

import com.example.caliste_proyecto.Abdomen1.AbdomenExercise;
import com.example.caliste_proyecto.DietasAc.Dietas;
import com.example.caliste_proyecto.Espalda1.EspaldaExercise;
import com.example.caliste_proyecto.Pecho1.PechoExercise;
import com.example.caliste_proyecto.Piernas1.PiernasExercise;
import com.example.caliste_proyecto.TodoCP.TodoExercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExerciseApiService {

    /*para brazo*/
    @GET("exercises")
    Call<List<Exercise>> getExercises();

    @GET("exercises/{id}")
    Call<Exercise> getExercise(@Path("id") int id);

    /*para abdomen*/
    @GET("api/abdomen_exercises")
    Call<List<AbdomenExercise>> getAbdomenExercises();

    @GET("abdomen_exercises/{id}")
    Call<AbdomenExercise> getAbdomenExercise(@Path("id") int id);

    /*para espalda*/
    @GET("api/espalda_ejer")
    Call<List<EspaldaExercise>> getEspaldaExercises();

    @GET("espalda_ejer/{id}")
    Call<EspaldaExercise> getEspaldaExercise(@Path("id") int id);

    /* para pecho*/
    @GET("api/pecho_ejer")
    Call<List<PechoExercise>> getPechoExercises();

    /*para piernas*/
    @GET("api/piernas_ejer")
    Call<List<PiernasExercise>> getPiernasExercises();


    /*todo el cuerpo*/
    @GET("api/todo_ejer")
    Call<List<TodoExercise>> getTodoExercises();

    /*dietas*/
    @GET("api/dietas")
    Call<List<Dietas>> getAllDietas();

}
