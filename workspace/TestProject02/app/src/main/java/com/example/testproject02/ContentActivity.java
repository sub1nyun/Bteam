package com.example.testproject02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContentActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        userDTO dto = (userDTO) intent.getSerializableExtra("user");
        fragment = new MainFragment(dto);
        changeFragment(new MainFragment(dto));
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1) {
                    fragment = new MainFragment(dto);
                    changeFragment(fragment);
                }else if (item.getItemId() == R.id.tab2) {
                    fragment = new ListFragment();
                    changeFragment(fragment);
                }else if (item.getItemId() == R.id.tab3) {
                    fragment = new LastFragment(ContentActivity.this);
                    changeFragment(fragment);
                }
                    return true;
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}