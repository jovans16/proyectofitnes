package com.example.caliste_proyecto.Registro;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {
    @POST("api/user/register")
    Call<ResponseBody> registerUser(@Body ServicioUsuario.RegistroUsuario user);

    @POST("api/user/login")
    Call<ResponseBody> loginUser(@Body UsuarioLogin user);
}
