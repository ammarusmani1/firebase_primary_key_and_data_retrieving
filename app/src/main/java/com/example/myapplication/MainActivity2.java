package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
            binding.button3.setOnClickListener(view -> {
        FirebaseDatabase.getInstance().getReference().child("Record").child(binding.editTextText6.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Map<String, String> getMap = (Map) snapshot.getValue();
                    String name = getMap.get("name");
                    String email = getMap.get("email");
                    String phone = getMap.get("phone");
                    String address = getMap.get("address");
                    String city = getMap.get("city");
                    binding.textView.setText(name);
                    binding.textView2.setText(email);
                    binding.textView3.setText(phone);
                    binding.textView4.setText(address);
                    binding.textView5.setText(city);
                } else {
                    Toast.makeText(MainActivity2.this, "data not present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    });

}
}
