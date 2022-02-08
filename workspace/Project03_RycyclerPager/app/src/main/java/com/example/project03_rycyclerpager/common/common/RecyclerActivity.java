package com.example.project03_rycyclerpager.common.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project03_rycyclerpager.common.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
RecyclerView rcv_act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rcv_act = findViewById(R.id.rcv_act);

        ArrayList<RecDTO> list = new ArrayList<>();
        list.add((new RecDTO(R.drawable.ic_launcher_foreground,"텍1")));
        list.add((new RecDTO(R.drawable.ic_launcher_background,"텍2")));
        list.add((new RecDTO(R.drawable.ic_launcher_foreground,"텍3")));
        list.add((new RecDTO(R.drawable.ic_launcher_background,"텍4")));
        list.add((new RecDTO(R.drawable.ic_launcher_foreground,"텍5")));


    }
}