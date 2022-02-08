package com.example.connectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {
    EditText edt_id, edt_pw, edt_name, edt_phone;
    Button join_btn, btn_cancel;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);

        btn_cancel = findViewById(R.id.cancel_btn);


        join_btn = findViewById(R.id.join_btn);
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MemberDTO dto = new MemberDTO(
                       Integer.parseInt(edt_id.getText()+""),
                       edt_pw.getText()+"",
                       edt_name.getText()+"",
                       edt_phone.getText()+""
               );

               //dto를 gson으로 바꾼다
               String json = gson.toJson(dto);
                Toast.makeText(JoinActivity.this, json, Toast.LENGTH_SHORT).show();
                AskTask task = new AskTask("bbbb.cos"); //json이란 생성자 없어서 오류
                task.addParam("dto", json);
                try {
                    InputStream in = task.execute().get();
                    MemberDTO dtoRecv = gson.fromJson(new InputStreamReader(in), MemberDTO.class);
                    Toast.makeText(JoinActivity.this, dtoRecv.getName(), Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}