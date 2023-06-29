package com.estevan.sintapujos;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.estevan.sintapujos.Interfaz.PersonaService;
import com.estevan.sintapujos.Models.Persona;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    //ActivitySignInBinding binding;
   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quiz-app-46393-default-rtdb.firebaseio.com");

    Retrofit retrofit;
    Button btnconsumo;
    EditText edtcedula, edtcorreo;
    String correou;
    String cedula;
    int id;
    TextView registerboton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        edtcedula = findViewById(R.id.edtCedula);
        edtcorreo = findViewById(R.id.edtcorreo);

        String BASE_URL = "https://sintapujos2023-rm77.onrender.com/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        btnconsumo = findViewById(R.id.btnConsumo);
        btnconsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = Integer.parseInt(edtcedula.getText().toString());
                correou = edtcorreo.getText().toString();
                cedula = edtcedula.getText().toString();
                obtenerDatos(id);

            }
        });

        registerboton = findViewById(R.id.register_button);
        registerboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sintapujos-2023.netlify.app/#/"));
                startActivity(intent);

            }
        });

    }

    private void obtenerDatos(int id) {

        PersonaService service = retrofit.create(PersonaService.class);
        Call<Persona> personaCall = service.getPersona(id);
        personaCall.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {

                try {

                    if (response.isSuccessful()){

                        Persona persona = response.body();
                        String correo = persona.getEmail();
                        String documento = persona.getN_documento();

                       // Toast.makeText(SignInActivity.this, ""+ correo, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(SignInActivity.this, ""+ documento, Toast.LENGTH_SHORT).show();

                        if (correo.equals(correou) && documento.equals(cedula)){

                            Toast.makeText(SignInActivity.this, "Inicio de sesion Exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                            startActivity(intent);

                        }else {

                            Toast.makeText(SignInActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                        }

                    }else {

                        Toast.makeText(SignInActivity.this, "Fallo", Toast.LENGTH_SHORT).show();

                    }

                }catch (Exception ex){

                    Toast.makeText(SignInActivity.this, ""+ ex.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {

                Log.e(TAG, "onFailure" + t.getMessage());

            }
        });
    }
}