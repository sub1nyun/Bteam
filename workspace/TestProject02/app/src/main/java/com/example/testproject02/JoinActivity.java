package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);



        EditText join_Id = findViewById(R.id.join_Id);
        EditText join_Pw = findViewById(R.id.join_Pw);
        EditText join_Name = findViewById(R.id.join_Name);
        EditText join_Birth = findViewById(R.id.join_Birth);

        Button Join_btn2=findViewById(R.id.Join_btn2);
       Button cancel_btn=findViewById(R.id.cancel_btn);

        userDTO dto = new userDTO(join_Id.getText()+"", join_Pw.getText()+"", join_Name.getText()+"", join_Birth.getText()+"");

       cancel_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(JoinActivity.this, "회원가입이 취소되었습니다.", Toast.LENGTH_SHORT).show();
               finish();
           }
       });
       Join_btn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String id = join_Id.getText()+"";
               String pw = join_Pw.getText()+"";
               String name= join_Name.getText()+"";
               String birth = join_Birth.getText()+"";

               if(id.length() ==0 || pw.length() ==0 || name.length() ==0 || birth.length()==0) {
                   Toast.makeText(JoinActivity.this, "값을 모두 입력하세요", Toast.LENGTH_SHORT).show();
               } else if(id.equalsIgnoreCase("Admin")) {
                   Toast.makeText(JoinActivity.this, "이미 가입된 아이디입니다.", Toast.LENGTH_SHORT).show();
                   join_Id.setText("");
                   join_Pw.setText("");
               }else {
                   Toast.makeText(JoinActivity.this, dto.getId()+"회원가입 성공", Toast.LENGTH_SHORT).show();
                   userDTO dto = new userDTO(join_Id.getText()+"", join_Pw.getText()+"", join_Name.getText()+"", join_Birth.getText()+"");
                   Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                   intent.putExtra("user",dto);
                   startActivity(intent);
               }


           }
       });
    }
}