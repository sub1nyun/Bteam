package com.example.project04_lastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project04_lastproject.common.AskTask;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edt_mapping);
        tv1 = findViewById(R.id.tv1);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask(editText.getText()+".te");
                task.execute();
                //그냥 생성해서 실행하면 됨
            }
        });
    }
}