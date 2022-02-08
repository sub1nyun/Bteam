package com.example.project03_rycyclerpager.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project03_rycyclerpager.common.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_rycycler, btn_viewpager, btn_viewpager2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btn_rycycler = findViewById(R.id.btn_recycler);
       btn_viewpager = findViewById(R.id.btn_viewpager);
       btn_viewpager2 = findViewById(R.id.btn_viewpager2);

        btn_rycycler.setOnClickListener(this);
        btn_viewpager.setOnClickListener(this);
        btn_viewpager2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_recycler){

        }else if(v.getId() == R.id.btn_viewpager){

        }else if(v.getId() == R.id.btn_viewpager2){

        }
    }
}