package com.example.project04_lastproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project04_lastproject.MainActivity;
import com.example.project04_lastproject.R;

public class LoginActivity extends AppCompatActivity {

    EditText edt_id, edt_pw;
    Button btn_login, btn_join;
    Intent intent;
    CheckBox chk_auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        chk_auto = findViewById(R.id.chk_auto);
        btn_join = findViewById(R.id.btn_join);

        SharedPreferences preferences = preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id", "--");
        String pw = preferences.getString("pw", "--");
        Boolean isLogin = preferences.getBoolean("autologin", false);
        chk_auto.setChecked(isLogin); //자동로그인을 체크하고나서 앱을 종료해도 그대로 저장된상태를 보여줌


        //getString String return -> String 변수에 담아줌
        //new - Interface 초기화
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText()+"";
                String pw = edt_pw.getText()+"";

                if(id.equals("aaa") && pw.equals("bbbb") ) {
                    saveLoginInfo(); //로그인 성공시 메소드 실행
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "입력정보 확인하세요", Toast.LENGTH_SHORT).show();
                    edt_id.setText("");
                    edt_pw.setText("");
                }
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추후 카메라 (Img WAS 전송 처리 부분)
                //Spring이 익숙해지고 나서 처리할 부분
            }
        });


        if(isLogin) {
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.callOnClick(); //OnClick을 강제로 실행함.
        }

    }

    public void saveLoginInfo() { //로그인 성공시 저장할 것
        //체크박스 사용 방법 -> is true false 리턴
        //체크박스 자동로그인이 체크가 된 상태라면 임시 데이터를 저장함 (로그인 정보를 )
        try {
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if(chk_auto.isChecked()) { //체크 -> 로그인 정보를 저장함
                //preferences =  //로그인 정보의 경우는 PRIVATE 사용
                //초기에 우리가 공유자원에 어떤 데이터를 key로 지정을 해서 넣어놓지 않은 상태
                //데이터가 존재하는지 먼저 확인
                 //Nasted Class 중첩클래스 new 초기화 아닌 만들어진 메소드로 사용함
                 editor.putBoolean("autologin", true);
                editor.putString("id", edt_id.getText()+ ""); //String 값 -> putString -> get으로 가져옴
                editor.putString("pw", edt_pw.getText()+ "");
                //editor.apply(); // 저장 확정 -> 파라매터 추가시 오류가 난다면 추가 하지 않기위해서 존재함
            }else { // 비활성 -> 로그인 정보를 삭제함
                //저장 정보 삭제 -> editor 필요
//                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("autologin");
                editor.remove("id");
                editor.remove("pw");
                //editor.putString("id", null);
                //editor.clear();
                //editor.apply();
            }
            editor.apply();
        }catch (Exception e) {
            Toast.makeText(LoginActivity.this, "자동로그인 정보 저장 실패", Toast.LENGTH_SHORT).show();

        }

    }
}