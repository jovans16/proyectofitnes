package com.example.caliste_proyecto.DietasAc;

import com.example.caliste_proyecto.ApiCallback;
import com.example.caliste_proyecto.ExerciseApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DietasExcerciseRepository {
    private ExerciseApiService ExerciseApiService; // Cambiado a DietasApiService

    public DietasExcerciseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.23.78:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ExerciseApiService = retrofit.create(ExerciseApiService.class); //
    }

    // Método para obtener todas las dietas
    public void getAllDietas(final ApiCallback<List<Dietas>> callback) { //
        ExerciseApiService.getAllDietas().enqueue(new Callback<List<Dietas>>() {
            @Override
            public void onResponse(Call<List<Dietas>> call, Response<List<Dietas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error al obtener las dietas");
                }
            }

            @Override
            public void onFailure(Call<List<Dietas>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

}
