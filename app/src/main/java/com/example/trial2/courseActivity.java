package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class courseActivity extends AppCompatActivity {

    ImageView course, home, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Intent intr = getIntent();
        home = findViewById(R.id.homeId);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(courseActivity.this, homeActivity.class);
                intent.putExtra("email",intr.getStringExtra("email"));
                intent.putExtra("name",intr.getStringExtra("name"));
                startActivity(intent);
            }
        });
        profile = findViewById(R.id.profileID);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(courseActivity.this, profileActivity.class);
                intent.putExtra("email",intr.getStringExtra("email"));
                intent.putExtra("name",intr.getStringExtra("name"));
                startActivity(intent);
            }
        });

    }

}