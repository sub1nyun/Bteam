package com.example.connectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SubActivity extends AppCompatActivity {
    EditText edt_data;
    Button btn_send;
    TextView tv_recv;
    ArrayList<String> params = new ArrayList<>();
    public static int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edt_data = findViewById(R.id.edt_data);
        btn_send = findViewById(R.id.btn_send);
        tv_recv = findViewById(R.id.tv_recv);

//        for(int i=0; i<10; i++) {
//            params.add("pvalue"+i);
//        }

        edt_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //btn_send를 클릭했을때 abc.cos 라는 매핑을 요청하고 결과를 TextView에 보여주기
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = Integer.parseInt(edt_data.getText().toString());
                for (int i =0; i<=num; i++) {
                    params.add("pvalue"+i);
                }
                AskTask task = new AskTask("vvv.cos");
                try {

                   task.execute().get();
                   // tv_recv.setText(data);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Ask가 멈췄다고 해서 main이 멈추면 안 되기때문에 비동기로 함 (따로 작업)
                //excute를 실행하면 비도이 작업으로 (Main Thread, AsynckTask는 따로 작업 )
                //서버랑 연동이 무조건 되고 나서 아래 코드가 실행되어야 할때 (결과가 무조건 필요할떄 )


            }
        });
    }




}