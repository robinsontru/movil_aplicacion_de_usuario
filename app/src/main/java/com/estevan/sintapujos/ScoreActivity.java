package com.estevan.sintapujos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.estevan.sintapujos.databinding.ActivityScoreBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quiz-app-46393-default-rtdb.firebaseio.com");

    int correct;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int counter = 0;
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        //Toast.makeText(this, ""+MenuActivity.alias, Toast.LENGTH_SHORT).show();
        //int correct = getIntent().getIntExtra("correctAnsw", 0);
        correct = QuestionsActivity.score;

       // int totalQuestion = getIntent().getIntExtra("totalQuestion", 0);
        int totalQuestion = QuestionsActivity.sizelist;

      //  int wrong = totalQuestion - correct;
        int wrong = QuestionsActivity.error;

        binding.totalRight.setText(String.valueOf(correct));
        binding.totalWrong.setText(String.valueOf(wrong));

        // aqui hay que insertar en la base de datos el correct y el alias
       // nombre =MenuActivity.alias;


        binding.totalQuestion.setText(String.valueOf(totalQuestion));

        binding.progressBar.setProgress(totalQuestion);
        binding.progressBar.setProgress(correct);

        Toast.makeText(this, ""+QuestionsActivity.score, Toast.LENGTH_SHORT).show();

        binding.progressBar.setProgressMax(totalQuestion);

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarpuntaje();

                Intent intent = new Intent(ScoreActivity.this,RankedActivity.class);
                startActivity(intent);
                //Intent intent = new Intent(ScoreActivity.this,MainActivity.class);
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                QuestionsActivity.score=0;
                QuestionsActivity.error=0;
             //   startActivity(intent);

            }
        });
        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              QuestionsActivity.score=0;
               QuestionsActivity.error=0;

                finish();



            }
        });
    }

    private void insertarpuntaje() {

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // compruebe si la identificacion no está registrado antes


                //enviar datos a la base de datos en tiempo real de firabase
                // estamos usando el número de identificacion  como identificación única de cada usuario
                //por lo que todos los demás detalles del usuario se incluyen en el número de identificacion
                String id = UUID.randomUUID().toString();
                databaseReference.child("ranking").child(id).child("alias").setValue(MenuActivity.alias);
                databaseReference.child("ranking").child(id).child("puntaje").setValue(correct);


                //muestra un éxito y luego termina la actividad
                Toast.makeText(ScoreActivity.this, "puntaje registrado", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}