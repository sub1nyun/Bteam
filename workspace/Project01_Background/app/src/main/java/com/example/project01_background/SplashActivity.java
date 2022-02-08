package com.example.project01_background;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //ProgressDialog <= 로딩 등 Dialog 화면 위에 띄워저서 어떤 처리를 하는것, Alert )
        //새로운 창이 아닌 기능을 띄우는 것 confirm 포함
        //사용자 <= 앱(어플)이 멈춘 상태가 되면 사용자는 당황함. 어떤 상태인지를 알 수가 없음
        //로딩중인지 진짜 멈춘건지
        //개발자 입장에서 제작 X -> 사용자 입장에서 만들어야 함( QA라는 직업이 따로있음)
        //Spinner (동그라미) ProgressDialog(진행률 o) 을 보여줄때 사용함
        //Context <= 어떤 액티비티(Context를 가진 클래스) 에서 보여줄것인지를 파라메터로 넘겨줘야함
        ProgressDialog dialog = new ProgressDialog(SplashActivity.this);
        //BackGround에서는 비정상적인 방법으로 Context를 강제로 만들어내도 화면에 보여줄 수가 없음
        dialog.setTitle("지금은 프로젝트를 로딩하는 중입니다....!");
        dialog.show();//<- 실제 Dialog를 보일 수 있게 하는 처리

        //몇초정도 현재 액티비티(Splash 기능이 없는 x)를 보여주고 나서..
        //실제 기능이 있는 MainActivity로 이동하는 처리
        //로고를 사용자에게 보여주려고도 사용함 - 마케팅
        //Android -> DB를 바로 못감 X
        //Android -> Sevlet (Service) -> DB
        //Web -> DB
        //시간이 더 걸리는 작업때 보여주기 위함
        //Handler 라는 메인 쓰레드를 이용해서 딜레이를 준다. (외울필요 X, 복붙해서 사용 )
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //메인으로 넘기는 처리 Intent이용 직접하기
                //Intent 넘기는 건 중요(암기하기) 현재 page.this, 넘어갈page.class -> startActivity(I);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();//dialog를 사용자가 터치하지 않아도 안보이게 처리
                finish();
                //finish();를 넣은 이유는 다시 뒤로 돌아더라도 splash가 실행되지 않게
            }
        }, 3000);


    }
}