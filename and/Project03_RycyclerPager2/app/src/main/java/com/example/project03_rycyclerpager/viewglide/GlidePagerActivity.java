package com.example.project03_rycyclerpager.viewglide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.project03_rycyclerpager.R;
import com.example.project03_rycyclerpager.common.AskTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GlidePagerActivity extends AppCompatActivity {

    //위젯을 xml에 추가 => 반드시 find해줌
    ViewPager2 glidepager2;
    DotsIndicator indicator;
    // ArrayList<Integer> imglist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_pager);

        glidepager2 = findViewById(R.id.glidepager2);
        indicator = findViewById(R.id.dots_indicator);

       // imglist.add(R.drawable.ic_launcher_background);
       // imglist.add(R.drawable.ic_launcher_foreground);
        //imglist.add(android.R.drawable.ic_dialog_email);
      //  imglist.add(android.R.drawable.ic_delete);
       // imglist.add(android.R.drawable.ic_menu_camera);


        //Viewpager2에 Adapter만들기 (ImageView 하나만 있으면 됨)
        //  == extends RecylcerView.Adapter<VH> (Nasted Class ViewHolder만들기)

        //  == count이용해서 같은 이미지 10개 넣어보고 모양 나오는지 먼저 확인 O
        //  == ArrayList<E> 만들어서 넣어보고 모양 나오는지 확인 O
        //  == 미들웨어에서 디비에 있는 데이터를 넣어서 가지고와서 보기
        //Viewpager2에 Adapter 연결하기.

        //indicator viewpager2에 연결하기

        // ======== Adapter에서 Glide이용해서 이미지 각각 다르게 보이게 하기
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
       // GlideAdapter adapter = new GlideAdapter(inflater, imglist);


        AskTask task = new AskTask("addd.amg");
        try {
            InputStream in = task.execute().get(); //get 결과를 받기 위해 필요함
            //Input 스트림에 데이터가 있는지 null인지 아닌지 체크
            if(in != null ) {
                Gson gson = new Gson();
                //dto(똑같은 구조를 갖은)
                ArrayList<AndImgDTO> list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<AndImgDTO>>(){}.getType());
                if(list.size() > 0) {

                    GlideAdapter adapter = new GlideAdapter(inflater, list, GlidePagerActivity.this);
                    glidepager2.setAdapter(adapter);
                    indicator.setViewPager2(glidepager2);

                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}