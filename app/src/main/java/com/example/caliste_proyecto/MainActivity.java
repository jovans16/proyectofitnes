package com.example.caliste_proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.example.caliste_proyecto.Registro.ServicioUsuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnContinuar, btnContinuarGoogle, btnCrearCuenta, btnAccesoDirecto;
    private static final int RC_SIGN_IN = 9002; // Código de solicitud para el inicio de sesión
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuarGoogle = findViewById(R.id.btnContinuarGoogle);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnAccesoDirecto = findViewById(R.id.btnAccesoDirecto);


      /*  btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
               String password = .getText().toString();  // Añadir input de contraseña*/

               // if (!email.isEmpty() && !password.isEmpty()) {
                    // Crear servicio de usuario
                    ServicioUsuario servicioUsuario = new ServicioUsuario();

                    // Crear objeto de registro
                    ServicioUsuario.RegistroUsuario registroUsuario;
                //registroUsuario = new ServicioUsuario().RegistroUsuario(email);

                // Hacer llamada a la API
                  /*  ServicioUsuario.UserApiService.registerUser(registroUsuario)
                         .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(MainActivity.this,
                                                "Registro exitoso",
                                                Toast.LENGTH_SHORT).show();
                                        // Navegar a la siguiente pantalla
                                    } else {
                                        try {
                                            String errorBody = response.errorBody().string();
                                            Toast.makeText(MainActivity.this,
                                                    "Error: " + errorBody,
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(MainActivity.this,
                                            "Error de conexión: " + t.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    Toast.makeText(MainActivity.this,
                            "Ingrese correo y contraseña",
                            Toast.LENGTH_SHORT).show();
                }
        });*/

       // Inicializar Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        // Configurar Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        // Configuro mi botón de inicio de sesión
        btnContinuarGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Iniciar sesión con Google", Toast.LENGTH_SHORT).show();
                signInWithGoogle();
            }

        });

    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Toast.makeText(this, "Error en el inicio de sesión: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();

        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        String idToken = acct.getIdToken();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // si el inicio es exitoso
                        Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        // Redirigo a la actividad SeccionesEntre
                        Intent intent = new Intent(MainActivity.this, SeccionesEntre.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // Si el inicio falla mostrar
                        Toast.makeText(MainActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                    }
                });

        // Lógica para autenticación de Google aquí */


        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Crear una nueva cuenta", Toast.LENGTH_SHORT).show();
                // Lógica para crear cuenta aquí
            }
        });

        btnAccesoDirecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeccionesEntre.class);
                startActivity(intent);
            }
        });
    }
}











