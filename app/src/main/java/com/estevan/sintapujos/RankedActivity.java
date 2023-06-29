package com.estevan.sintapujos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.estevan.sintapujos.Models.Rankings;
import com.estevan.sintapujos.Models.Users;
import com.estevan.sintapujos.databinding.ActivityRankedBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RankedActivity extends AppCompatActivity {

    ActivityRankedBinding binding;

    FirebaseDatabase database;

    DatabaseReference myRef;

    ArrayList<Rankings> lista = new ArrayList<>();
    ArrayAdapter<Rankings> adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // binding = ActivityRankedBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_ranked);
        listView = findViewById(R.id.listRanking);
        getSupportActionBar().hide();
        mostrarranking();

    }

    private void mostrarranking() {

      //  Toast.makeText(this, "Holaa", Toast.LENGTH_SHORT).show();

        inicializar();

        Query query = myRef.child("ranking").orderByChild("puntaje").limitToLast(10);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lista.clear();
                for (DataSnapshot objranking : snapshot.getChildren()){
                    Rankings valores = objranking.getValue(Rankings.class);
                   // Toast.makeText(RankedActivity.this, ""+valores, Toast.LENGTH_SHORT).show();
                    lista.add(valores);

                  //adapter = new ArrayAdapter<Rankings>(RankedActivity.this, android.R.layout.simple_list_item_1,lista);
                    //listView.setAdapter(adapter);
                }

                Collections.reverse(lista);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new ArrayAdapter<Rankings>(RankedActivity.this, android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adapter);

    }

    private void inicializar() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }

    public void back(View view){
        Intent back = new Intent(this, MenuActivity.class);
        startActivity(back);
    }

}