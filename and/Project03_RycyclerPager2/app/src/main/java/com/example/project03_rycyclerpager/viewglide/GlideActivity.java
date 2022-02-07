package com.example.project03_rycyclerpager.viewglide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project03_rycyclerpager.R;
import com.example.project03_rycyclerpager.common.AskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GlideActivity extends AppCompatActivity {
    ImageView glidimgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        glidimgv = findViewById(R.id.glide_imgv);

        Glide.with(this).load("https://www.codingfactory.net/wp-content/uploads/abc.jpg").into(glidimgv);

        AskTask task = new AskTask("addd.amg");
        try {
            InputStream in = task.execute().get(); //get 결과를 받기 위해 필요함
            //Input 스트림에 데이터가 있는지 null인지 아닌지 체크
            if(in != null ) {
                Gson gson = new Gson();
                //dto(똑같은 구조를 갖은)
                ArrayList<AndImgDTO> list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<AndImgDTO>>(){}.getType());
                if(list.size() > 0) {
                    Glide.with(this).load(list.get(1).getImg_url()).into(glidimgv);
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}