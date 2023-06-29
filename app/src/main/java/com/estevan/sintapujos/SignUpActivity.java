package com.estevan.sintapujos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.estevan.sintapujos.databinding.ActivitySignUpBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quiz-app-46393-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        final EditText fullname = findViewById(R.id.name);
        final EditText identify = findViewById(R.id.identify);
        final EditText email = findViewById(R.id.Email);
        final EditText password = findViewById(R.id.password);
        final EditText conpassword = findViewById(R.id.conpassword);
        final Button register_button = findViewById(R.id.Register_btn);
        final TextView loginnow_button = findViewById(R.id.loginnow_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //obtener los datos de los txt en String variables
                final String fullnameTxt = fullname.getText().toString();
                final String identyfyTxt = identify.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conpasswordTxt = conpassword.getText().toString();

                //compruebar si el usuario completa todos los campos antes de enviar datos a firebase
                if(fullnameTxt.isEmpty() || identyfyTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Por favor llena todos los datos vacios", Toast.LENGTH_SHORT).show();
                }

                // verificar si la contraseña coinsiden entre si
                // si no coinciden entre sí, muestre un mensaje
                else if(!passwordTxt.equals(conpasswordTxt)){
                    Toast.makeText(SignUpActivity.this, "Las contraseñas no coinsiden", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // compruebe si la identificacion no está registrado antes
                            if(snapshot.hasChild(identyfyTxt)) {
                                Toast.makeText(SignUpActivity.this, "el numero de identificacion ya esta registrado", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //enviar datos a la base de datos en tiempo real de firabase
                                // estamos usando el número de identificacion  como identificación única de cada usuario
                                //por lo que todos los demás detalles del usuario se incluyen en el número de identificacion
                                databaseReference.child("users").child(identyfyTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(identyfyTxt).child("number").setValue(identyfyTxt);
                                databaseReference.child("users").child(identyfyTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(identyfyTxt).child("password").setValue(passwordTxt);

                                //muestra un éxito y luego termina la actividad
                                Toast.makeText(SignUpActivity.this, "usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        loginnow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}