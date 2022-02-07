package com.example.connectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView tv1;

    //1. Manifest 확인 (clearTrafic, Permission , Lib 등) <-기존에 되어있던 소스가 있으면 복붙
        // Permission (사용 권한을 부여 받는 것, 별도의 코드가 필요한 경우가있고, 아닌 경우가 있음
        // ex. 인터넷은 그냥 Manifest에 기재만해도 사용이가능 , 카메라,저장소 등은 사용자 동의
        // clearTrafic = http, https 로 요청을 할 수 있게 처리.

    //2. Gradle (라이브러리 추가)-> sync now 통해서 연결해주기
    // 메모장 복붙 (2단계)
    //3. ※ AskTask를 사용해서 Eclipse까지 접근 ( WAR ) Mapping을 통해서!
    //service <- 비동기 처리. (메인쓰레드 액티비티) <- 멈추지 않고 어떤 동작을 하기 위해서
    public   TextView tv_data; //비추천 static

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      editText = findViewById(R.id.edt_mapping);
      tv_data = findViewById(R.id.tv1);
      tv1 = findViewById(R.id.tv1);
       findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                //AskTask실행해주면 됨
               AskTask task = new AskTask(editText.getText()+".cos");
//
               task.execute(); //비동기 방식이라 실행 후 다음줄로 넘어감
           }
       });
    }

    public void changText(String data) {

        tv_data.setText(data);
    }


    //액티비티는 이미 생성이 되어있어서 new로 초기화 하는것이 아닌
    //위젯을 찾아줘야함
}