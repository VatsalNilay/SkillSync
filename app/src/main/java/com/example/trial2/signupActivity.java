package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trial2.data.DBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class signupActivity extends AppCompatActivity {

  private  ImageView backBtn;
  private DBHelper databaseHelper;
  private TextView na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        na = findViewById(R.id.textViewAlreadyHaveAccount);
        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( signupActivity.this ,loginActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DBHelper(this);
        final TextInputLayout textInputLayoutUsername = findViewById(R.id.textInputLayoutName);
        final TextInputEditText editTextUsername = findViewById(R.id.editTextName);

        final TextInputLayout textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        final TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);

        final TextInputLayout textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        final TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        Button signUpButton = findViewById(R.id.buttonSubmit);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String dob = "21012001";
                if (validateInputs(username, email, password)) {
                    Log.d("ye chalega","ee");
                    if (!databaseHelper.doesUserExist(email)) {
                        // User does not exist, proceed with signup
                        Log.d("ho raha","ho raha");
                        databaseHelper.insertUser(username, email, password,dob, signupActivity.this);

                        Log.d("ho gaya" ,"ho raha");
                        finish();
                    } else {
                        // User already exist
                        textInputLayoutEmail.setError("Email is already registered");

                    }
                }
            }
            });
        }
        private boolean validateInputs(String username, String email, String password) {
            // Implement your validation logic here
            // For simplicity, just checking if fields are not empty
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                // Show error messages or handle validation accordingly
                return false;
            }
            return true;
        }




}
