package com.estevan.sintapujos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.estevan.sintapujos.Adapters.CategoryAdapter;
import com.estevan.sintapujos.Adapters.GrideAdapter;

import com.estevan.sintapujos.databinding.ActivitySetsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class SetsActivity extends AppCompatActivity {

    ActivitySetsBinding binding;
    FirebaseDatabase database;

    GrideAdapter adapter;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        key = getIntent().getStringExtra("key");

       adapter = new GrideAdapter(CategoryAdapter.setnum,CategoryAdapter.category);
       getIntent().getStringExtra("category");

       binding.gridView.setAdapter(adapter);

    }

    public void backk(View view){
        Intent backk = new Intent(this, MenuActivity.class);
        startActivity(backk);
    }
}