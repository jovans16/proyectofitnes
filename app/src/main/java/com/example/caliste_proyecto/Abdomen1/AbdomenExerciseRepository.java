package com.example.caliste_proyecto.Abdomen1;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.ExerciseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AbdomenExerciseRepository {

    private ExerciseApiService exerciseApiService;

    // Constructor correcto para la clase
    public AbdomenExerciseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.23.78:5000/") // Cambiar la URL si es necesario a 10.0.2.2 retomar la del tec al conectar
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApiService = retrofit.create(ExerciseApiService.class);
    }

    // Método para obtener ejercicios de abdomen
    public void getAllAbdomenExercises(final ApiCallback<List<AbdomenExercise>> callback) {
        exerciseApiService.getAbdomenExercises().enqueue(new Callback<List<AbdomenExercise>>() {
            @Override
            public void onResponse(Call<List<AbdomenExercise>> call, Response<List<AbdomenExercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener ejercicios de abdomen");
                }
            }

            @Override
            public void onFailure(Call<List<AbdomenExercise>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
    /*public void getExercise(int id, final ApiCallback<Exercise> callback) {
        exerciseApiService.getExercise(id).enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Ejercicio no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }*/
}

