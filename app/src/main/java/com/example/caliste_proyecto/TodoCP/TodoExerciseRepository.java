package com.example.caliste_proyecto.TodoCP;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.ExerciseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoExerciseRepository {
    private ExerciseApiService exerciseApiService;

    // Constructor correcto para la clase
    public TodoExerciseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.23.78:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        exerciseApiService = retrofit.create(ExerciseApiService.class);
    }

    // Método para obtener ejercicios
    public void getAllTodoExercises(final ApiCallback<List<TodoExercise>> callback) {
        exerciseApiService.getTodoExercises().enqueue(new Callback<List<TodoExercise>>() {
            @Override
            public void onResponse(Call<List<TodoExercise>> call, Response<List<TodoExercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener ejercicios de todo el cuerpo");
                }
            }

            @Override
            public void onFailure(Call<List<TodoExercise>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
