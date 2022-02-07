package com.example.connectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {
Gson gson = new Gson();
    Button btn_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<MemberDTO> list = new ArrayList<>();
                for(int i=0; i<20; i++) {
                    list.add(new MemberDTO(i, "pw"+i, "name"+i, "phone"+i));
                }
                //String으로 바꿔서 왔다 갔다
                MainAskTask task = new MainAskTask("ad.list");
                Toast.makeText(ListActivity.this, gson.toJson(list), Toast.LENGTH_SHORT).show();
                task.addParam("list", gson.toJson(list));
                try {

                    InputStream in =  task.execute().get();   //미들웨어에서 반드시 데이터를 받고나서 밑에줄 코드로
                    list =  gson.fromJson(new InputStreamReader(in)
                            , new TypeToken<List<MemberDTO>>() {}.getType() );
                    String aa = "";
                    //가야하는 상황은 (대부분) 반드시 gete을 통해 작업한다.
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}