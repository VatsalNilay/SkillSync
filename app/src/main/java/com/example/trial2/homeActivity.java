package com.example.trial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeActivity extends AppCompatActivity {
TextView name1;
ImageView course, home, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Intent intr = getIntent();
//        String name = intr.getStringExtra("name");
//
//
//        name1 = findViewById(R.id.showUsername);
//        name1.setText(name);
//
//        course = findViewById(R.id.courseId);
//        course.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(homeActivity.this, courseActivity.class);
//                intent.putExtra("email",intr.getStringExtra("email"));
//                intent.putExtra("name",name);
//                startActivity(intent);
//            }
//        });
//
//        profile = findViewById(R.id.profileID);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(homeActivity.this, profileActivity.class);
//                intent.putExtra("email",intr.getStringExtra("email"));
//                intent.putExtra("name",name);
//                startActivity(intent);
//            }
//        });
        BottomNavigationView nav;
        nav = findViewById(R.id.nav);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_home)
                {
                    loadFrag(new fragment_home(), true);
                }
                else if(id == R.id.nav_courses)
                {
                    loadFrag(new fragment_course(), false);
                }
                else if(id == R.id.nav_alumni)
                {
                    loadFrag(new fragment_alumni(),false);
                }
                else
                {
                    loadFrag(new fragment_profile(), false);
                }
                return true;

            }
        });
        nav.setSelectedItemId(R.id.nav_home);
    }
    public void loadFrag(Fragment frag, boolean flag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag == true)
        {
            ft.add(R.id.container, frag);
        }
        else
        {
            ft.replace(R.id.container, frag);
        }

        ft.commit();
    }

}