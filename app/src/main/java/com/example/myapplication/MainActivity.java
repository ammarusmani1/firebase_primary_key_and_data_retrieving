package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
int maxId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase.getInstance().getReference().child("Record").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxId = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.button.setOnClickListener(view -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", binding.editTextText.getText().toString());
            map.put("email", binding.editTextText2.getText().toString());
            map.put("link", binding.editTextText3.getText().toString());
            map.put("address", binding.editTextText4.getText().toString());
            map.put("city", binding.editTextText5.getText().toString());

            FirebaseDatabase.getInstance().getReference().child("Record").child(String.valueOf(maxId++)).setValue(map);


            binding.editTextText.setText("");
            binding.editTextText2.setText("");
            binding.editTextText3.setText("");
            binding.editTextText4.setText("");
            binding.editTextText5.setText("");
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });


    }
}