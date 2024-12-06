package com.example.caliste_proyecto.Registro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioUsuario {
    public static com.example.caliste_proyecto.Registro.UserApiService UserApiService;
    private Retrofit retrofit;
    private UserApiService userApiService;

    public ServicioUsuario() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")  // URL de tu API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApiService = retrofit.create(UserApiService.class);
    }

    public static class RegistroUsuario {

        private String email;
        private String password;


    }
}