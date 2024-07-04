package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.trial2.data.DBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {

    private ImageView backBtn;
    private DBHelper databaseHelper;
    private TextView na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        na = findViewById(R.id.textViewNotRegistered);
        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DBHelper(this);

        final TextInputLayout textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        final TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);

        final TextInputLayout textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        final TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);

        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                Log.d("jeeja","jeeja");
                if(validateInputs(email, password))
                {
                    if(!databaseHelper.loginUser(email,password))
                    {
                        Toast.makeText(loginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(loginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show() ;
                        Intent intent  = new Intent(loginActivity.this, homeActivity.class);
                        intent.putExtra("name",  databaseHelper.getUserName(email));
                        intent.putExtra("email",  email);
                        startActivity(intent);
                    }


                }
                else { finish();}
            }
        });
    }

    private boolean validateInputs(String email, String password) {
        // Implement your validation logic here
        return !email.isEmpty() && !password.isEmpty();
    }
}