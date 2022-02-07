package com.example.project03_rycyclerpager.viewpager;

//Adapter가 완전히 똑같은 형태로 사용이 가능함
//RecyclerView와 완전히 유사함

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.R;
import com.example.project03_rycyclerpager.recycler.RecDTO;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.ViewHolder> {

    //ArrayList<RecDTO> list;
    LayoutInflater inflater;
    //inflater를 만들 수 있는 Context를 받아오거나, LayoutInflater자체를 받아오기
    //토스트창 보여줄떄는 Context가 있어야함

    public Pager2Adapter( LayoutInflater inflater) {
       // this.list = list;
        this.inflater = inflater;
    }
    //뷰홀더란 우리가 만든 클래스르 말함
    //칸마다 보여줄 데이터를 연결해서 ViewHolder라는 클래스 형태로 묶어둠
    //칸마다 들어갈 정보 - 뷰그룹
    //LayoutInflater ↓
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item,parent, false );
        //ViewHolder viewHolder = new ViewHolder(itemView); 생성자를 리턴해도 똑같음
        return new ViewHolder(itemView);
    }
    //묶어진 데이터가 정상적으로 View(Recycler, Pager2 .. 등) 바인딩(붙고나서) 되고 나서 처리
    //이벤트 처리 (Onclick 등등)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      // holder.rec_imgv.setImageResource(list.get(position).getRedId());
      // holder.rec_tv.setText(list.get(position).getTextStr());
    }
    //몇개의 데이터를 보여줄건지를 정의
    //ArrayList나 인덱스(크기)를 가진 데이터를 기준으로 정함
    @Override
    public int getItemCount() {
        return 20;
        //return list.size();
    }

    //1. InnerClass로 VH (ViewHolder)클래스 만들기 - 상속때 복잡한 과정 건너 뛰기위함
    //상속을 받아야 함
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rec_imgv;
        TextView rec_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_imgv = itemView.findViewById(R.id.rec_imgv);
            rec_tv = itemView.findViewById(R.id.rec_tv);
        }
    }

}
