package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.trial2.data.DBHelper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Button buttonLogin = findViewById(R.id.login);
        Button buttonSignup = findViewById(R.id.signup);

//        try {
//            dbHelper.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        dbHelper.openDataBase();
        // Assuming you have a Button with id "buttonLogin" in your layout


        // Set click listener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When login button is clicked, navigate to HomeActivity
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
        // Set click listener for the Signup button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When login button is clicked, navigate to HomeActivity
                Intent intent = new Intent(MainActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });
    }
}
