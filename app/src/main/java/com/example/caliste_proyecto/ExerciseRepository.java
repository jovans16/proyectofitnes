package com.example.caliste_proyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExerciseRepository {

    private ExerciseApiService exerciseApiService;

    public ExerciseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/") // Cambiar la IP si es necesario a 10.0.2.2 retomar la del tec al conectar
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApiService = retrofit.create(ExerciseApiService.class);
    }

    public void getAllExercises(final ApiCallback<List<Exercise>> callback) {
        exerciseApiService.getExercises().enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener ejercicios");
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getExercise(int id, final ApiCallback<Exercise> callback) {
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
    }
}


