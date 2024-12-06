package com.example.caliste_proyecto.Pecho1;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.ExerciseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PechoExerciseRepository {
    private ExerciseApiService exerciseApiService;

    // Constructor correcto para la clase
    public PechoExerciseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.23.78:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApiService = retrofit.create(ExerciseApiService.class);
    }

    // Método para obtener ejercicios de abdomen
    public void getAllPechoExercises(final ApiCallback<List<PechoExercise>> callback) {
        exerciseApiService.getPechoExercises().enqueue(new Callback<List<PechoExercise>>() {
            @Override
            public void onResponse(Call<List<PechoExercise>> call, Response<List<PechoExercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener ejercicios de espalda");
                }
            }

            @Override
            public void onFailure(Call<List<PechoExercise>> call, Throwable t) {
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

