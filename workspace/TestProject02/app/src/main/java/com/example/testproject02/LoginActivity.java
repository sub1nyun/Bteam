package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Button Login_btn;
    Button Join_btn;
    userDTO dto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        userDTO dto = (userDTO) intent.getSerializableExtra("user");

//        ArrayList<userDTO> list = (ArrayList<userDTO>) intent.getSerializableExtra("user");



//        for (int i = 0 ; i < list.size(); i ++){
//            Toast.makeText(LoginActivity.this, list.get(i).getId()+"", Toast.LENGTH_SHORT).show();
//        }




       EditText idText = findViewById(R.id.Login_id);

       EditText pwText = findViewById(R.id.Login_pw);

       Login_btn= findViewById(R.id.Login_btn);
       Join_btn=findViewById(R.id.Join_btn);
       Login_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String userId = idText.getText()+"";
               String userPw = pwText.getText()+"";
               if(userId.equalsIgnoreCase("Admin") && userPw.equalsIgnoreCase("Admin")) {
                   Toast.makeText(getApplicationContext(), userId+" 로그인 성공", Toast.LENGTH_SHORT).show();
                   userDTO admin = new userDTO("Admin", "Admin", "윤수빈", "0708");
                   Intent intent1 = new Intent(LoginActivity.this, ContentActivity.class);
                   intent1.putExtra("user", admin);
                   startActivity(intent1);
               }else if(dto != null && userId.equals(dto.getId()) && userPw.equals(dto.getPw())) {
                   Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
                   Intent intent1 = new Intent(LoginActivity.this, ContentActivity.class);
                   intent1.putExtra("user", dto);
                   startActivity(intent1);
               }else {
                   Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
               }
           }
       });

        Join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });




    }
}