package com.example.project01_background;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main:";
    Button btn_alert;
    //ProgressBar pb_bar;
    static ProgressBar pb_bar;
    boolean boom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alert = findViewById(R.id.btn_alert);
        pb_bar = findViewById(R.id.pg_bar);

        btn_alert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //AlertDialog <=AlertDialog.Buider (만들수있는 객체로 설정을 해두고 <- 를 이용해서 만듬)
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("안내 ");
            builder.setMessage("Service를 이용해서 파일을 다운로드!!"); //보여줄 부분
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 //예를 눌렀을때 처리
                 // 컴포넌트 끼리 데이터를 이동하거나 컴포넌트를 실행하는 객체 : Intent
                    // setter & getter ?
                    //if(pg_bar.getProgress() ==100 || pg_bar.getProgress() ==0)
                    Log.d(TAG, "onClick: " + which);

                    //1.ProgressBar 객체를 다른 클래스에서 바로 (Instence화 X)
                    //접근이 가능하게 만들면 됨
                    //2. for문을 이용한 i값을
                    //3. 파일 다운로드가 시작되면 다시 예를 눌러도 안 되게끔 처리 -- 추후

                    if(boom == false) {
                        boom = true;
                        Intent intent = new Intent(MainActivity.this, FileDownLoadService.class);
                        //명시적 인텐트 ▲
                        intent.putExtra("key", "작업을 시작합니다");
                        startService(intent);
                    }

                }
            });
            builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //Create 전에 오는 값은 상관 없음
            AlertDialog dialog = builder.create();// builder.create < return AlertDialog
            dialog.show();
        }
    });
    //Service가 중요한 게 아닌
        // => HttpClient요청 ==>( AsyncTask( 난이도 상 ) ) 백그라운드에서 동작을 하게 만드는 거(개념)
        //Mainfest - service
    }
}