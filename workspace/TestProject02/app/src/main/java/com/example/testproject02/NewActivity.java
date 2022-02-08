package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Button close_btn;
    EditText nstr1, nstr2, ndto1, ndto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        close_btn = findViewById(R.id.close_btn);


       nstr1 = findViewById(R.id.String_tv1);
       nstr2 = findViewById(R.id.String_tv2);

       ndto1 =  findViewById(R.id.dto_tv1);
       ndto2 = findViewById(R.id.dto_tv2);

        Intent intent = getIntent();
        nstr1.setText(intent.getStringExtra("str1"));
        nstr2.setText(intent.getStringExtra("str2"));
        LastDTO dto = (LastDTO) intent.getSerializableExtra("dto");
        ndto1.setText(dto.getLtv1());
        ndto2.setText(dto.getLtv2());

    close_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });













    }
}