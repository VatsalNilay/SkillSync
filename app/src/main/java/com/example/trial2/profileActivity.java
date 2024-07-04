package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {
    ImageView course, home, profile;
    TextView user, emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intr = getIntent();
        String userName = intr.getStringExtra("name");
        String emailAdd = intr.getStringExtra("email");
        user = findViewById(R.id.name);
        user.setText(userName);
        emails = findViewById(R.id.email);
        emails.setText(emailAdd);


        home = findViewById(R.id.homeId);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileActivity.this, homeActivity.class);
                intent.putExtra("email",intr.getStringExtra("email"));
                intent.putExtra("name",intr.getStringExtra("name"));
                startActivity(intent);
            }
        });
        course = findViewById(R.id.courseId);
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileActivity.this, courseActivity.class);
                intent.putExtra("email",intr.getStringExtra("email"));
                intent.putExtra("name",intr.getStringExtra("name"));
                startActivity(intent);
            }
        });

        TextView signoutTextView = findViewById(R.id.signout);
        signoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform sign-out logic here
                signOut();
            }
        });
    }
        private void signOut() {
            // Implement your sign-out logic
            // For example, you might want to clear user session, navigate to login screen, etc.
            // Here, we are finishing the current activity (assuming it's a login screen)
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        }
}